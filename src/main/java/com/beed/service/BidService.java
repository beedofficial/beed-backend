package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;
import com.beed.repository.BidRepository;
import com.beed.utility.BidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public AppUserDto higgestBidderInfo(long auctionId) throws Exception {
        return convertUserEntityToDto(bidRepository.findHiggestBidder(auctionId));
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
        return bidRepository.getProfileHistoryBids(userId, pageWithTenElements);
    }

    public boolean deleteBidById(Long Id) throws Exception{
        bidRepository.deleteById(Id);
        return bidRepository.findById(Id).isEmpty();
    }

    public List<BidDto> getBidList(Integer page) throws Exception {
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        return bidRepository.getBidsInfos(pageWithTenElements);
    }
}
