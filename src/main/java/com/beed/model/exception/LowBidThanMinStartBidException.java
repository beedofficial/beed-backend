package com.beed.model.exception;

import static com.beed.model.constant.Error.BID_LOWER_THAN_MIN_START_BID;

public class LowBidThanMinStartBidException extends Exception {
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LowBidThanMinStartBidException() {
        super(BID_LOWER_THAN_MIN_START_BID.toString());
        this.setStatusCode(BID_LOWER_THAN_MIN_START_BID.getCode());
    }
}
