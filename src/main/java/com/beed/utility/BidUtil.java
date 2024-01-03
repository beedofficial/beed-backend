package com.beed.utility;

import com.beed.model.dto.BidDto;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;

import java.time.OffsetDateTime;

public class BidUtil {
    public static BidDto convertBidToDto (Bid bid){
        return BidDto.builder()
                .id(bid.getId())
                .auction(bid.getAuction())
                .bidder(bid.getBidder())
                .amount(bid.getAmount())
                .date(bid.getDate())
                .build();
    }

    public static Bid createBid(Long auctionId, Long bidderId, Long amount) {
        AppUser bidder = AppUser.builder()
                .id(bidderId)
                .build();
        Auction auction = Auction.builder()
                .id(auctionId)
                .build();
        return Bid.builder()
                .bidder(bidder)
                .auction(auction)
                .date(OffsetDateTime.now())
                .amount(amount)
                .build();
    }
}
