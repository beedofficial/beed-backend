package com.beed.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBidRequest {
    private Long auctionId;
    private Long bidAmount;
    private Long auctioneerId;
    private Long minStartBid;
}
