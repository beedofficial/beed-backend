package com.beed.utility;

import com.beed.model.dto.BidDto;
import com.beed.model.entity.Bid;

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
}
