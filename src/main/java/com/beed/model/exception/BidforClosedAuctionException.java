package com.beed.model.exception;

import static com.beed.model.constant.Error.BIDDING_CLOSED_AUCTION_ERROR;

public class BidforClosedAuctionException extends Exception {
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public BidforClosedAuctionException() {
        super(BIDDING_CLOSED_AUCTION_ERROR.toString());
        this.setStatusCode(BIDDING_CLOSED_AUCTION_ERROR.getCode());
    }
}
