package com.beed.model.exception;

import static com.beed.model.constant.Error.BID_LOWER_THAN_HIGHEST_BID;

public class LowBidThanHighestBidException extends Exception {
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LowBidThanHighestBidException() {
        super(BID_LOWER_THAN_HIGHEST_BID.toString());
        this.setStatusCode(BID_LOWER_THAN_HIGHEST_BID.getCode());
    }
}
