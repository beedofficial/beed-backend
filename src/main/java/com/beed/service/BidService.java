package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;
import com.beed.model.exception.BidforOwnAuctionException;
import com.beed.model.exception.LowBidThanHighestBidException;
import com.beed.model.exception.LowBidThanMinStartBidException;
import com.beed.model.request.CreateBidRequest;
import com.beed.repository.BidRepository;
import com.beed.utility.BidUtil;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.beed.utility.AppUserUtil.convertUserEntityToDto;
import static com.beed.utility.BidUtil.convertBidToDto;

@Service
public class BidService {
    @Autowired
    BidRepository bidRepository;

    @Autowired
    AppUserService appUserService;

    public BidDto getBidById(Long Id){
        Bid bid = bidRepository.findById(Id).orElse(null);
        if (bid != null){
            return convertBidToDto(bid);
        }else{
            return null;
        }
    }

    public AppUserDto getHighestBidderInfo(long auctionId) throws Exception {
        return convertUserEntityToDto(bidRepository.findHighestBidder(auctionId));
    }

    public List<BidDto> getBidsByBidder(long bidderId) {
        List<Bid> bids = bidRepository.findByBidder(bidderId);
        return bids.stream()
                .map(BidUtil::convertBidToDto)
                .collect(Collectors.toList());
    }


    public void createNewBid(BidDto bidDto) throws Exception {
        Bid bid = new Bid();

        bid.setAuction(bidDto.getAuction());
        bid.setBidder(bidDto.getBidder());
        bid.setAmount(bidDto.getAmount());
        bid.setDate(bidDto.getDate());

        bidRepository.save(bid);
    }

    public List<BidDto> getAllBids() {
        List<Bid> bids = bidRepository.findAll();
        return bids.stream().map(BidUtil::convertBidToDto).toList();
    }

    public BidDto getHighestBidForAuction(long auctionId) {
        Bid highestBid = bidRepository.findTopByAuctionOrderByAmountDesc(auctionId);
        if (highestBid != null) {
            return convertBidToDto(highestBid);
        } else {
            return null;
        }
    }

    public List<ProfileHistoryBidDto> getProfileHistoryBids(String username, Integer page) {
        Long userId = appUserService.getUserIdByUsername(username);
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        List<ProfileHistoryBidDto> profileHistoryBidList = bidRepository.getProfileHistoryBids(userId, pageWithTenElements);
        profileHistoryBidList.forEach(bid -> {
            bid.setDone(OffsetDateTime.now().isAfter(bid.getAuctionEndDate()));
        });
        return profileHistoryBidList;
    }

    public boolean deleteBidById(Long Id) throws Exception{
        bidRepository.deleteById(Id);
        return bidRepository.findById(Id).isEmpty();
    }

    public List<BidDto> getBidList(Integer page) throws Exception {
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        return bidRepository.getBidsInfos(pageWithTenElements);
    }

    public Long getHighestBidValue(Long auctionId){
        Bid highestBid = bidRepository.findTopByAuctionOrderByAmountDesc(auctionId);
        if (highestBid != null) {
            return highestBid.getAmount();
        } else {
            return null;
        }
    }


    public Long addBid(CreateBidRequest createBidRequest, String bidderUsername) throws Exception {
        Long bidderId = appUserService.getUserIdByUsername(bidderUsername);
        Long previousBidderId = 0L;

        if(Objects.equals(bidderId, createBidRequest.getAuctioneerId())) { //checking if user tries to bid its own auction
            throw new BidforOwnAuctionException();
        }
        else {
            Long highestBidAmount = this.getHighestBidValue(createBidRequest.getAuctionId());
            if(highestBidAmount != null) {
                if (highestBidAmount >= createBidRequest.getBidAmount()){
                    throw new LowBidThanHighestBidException();
                }
                else {
                    Bid newBid = BidUtil.createBid(
                            createBidRequest.getAuctionId(),
                            bidderId,
                            createBidRequest.getBidAmount());
                    bidRepository.save(newBid);

                    previousBidderId = bidRepository.getHighestBidderId(createBidRequest.getAuctionId());
                }
            }
            else {
                if(createBidRequest.getMinStartBid() > createBidRequest.getBidAmount()){
                    throw new LowBidThanMinStartBidException();
                }
                else{
                    Bid newBid = BidUtil.createBid(
                            createBidRequest.getAuctionId(),
                            bidderId,
                            createBidRequest.getBidAmount());
                    bidRepository.save(newBid);
                }
            }
        }

        return previousBidderId;
    }

}
