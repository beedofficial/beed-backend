package com.beed.service;

import com.beed.model.dto.BidDto;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;
import com.beed.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidService {
    @Autowired
    BidRepository bidRepository;

    public static BidDto convertBidToDto (Bid bid){
        return BidDto.builder()
                .id(bid.getId())
                .auction(bid.getAuction())
                .bidder(bid.getBidder())
                .amount(bid.getAmount())
                .date(bid.getDate())
                .build();
    }

    public BidDto getBidById(Long Id){
        Bid bid = bidRepository.findById(Id).orElse(null);
        if (bid != null){
            return convertBidToDto(bid);
        }else{
            return null;
        }
    }

    public List<BidDto> getBidsByBidder(AppUser bidder) {
        List<Bid> bids = bidRepository.findByBidder(bidder);
        return bids.stream()
                .map(BidService::convertBidToDto)
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
        return bids.stream().map(BidService::convertBidToDto).toList();
    }

    public BidDto getHighestBidForAuction(Auction auction) {
        Bid highestBid = bidRepository.findTopByAuctionOrderByAmountDesc(auction);
        if (highestBid != null) {
            return convertBidToDto(highestBid);
        } else {
            return null;
        }
    }
}
