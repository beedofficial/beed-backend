package com.beed.model.exception;

import static com.beed.model.constant.Error.BIDDING_OWNED_AUCTION;

public class BidforOwnAuctionException extends Exception{
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public BidforOwnAuctionException() {
        super(BIDDING_OWNED_AUCTION.toString());
        this.setStatusCode(BIDDING_OWNED_AUCTION.getCode());
    }
}
