package com.beed.model.exception;

import static com.beed.model.constant.Error.USERNAME_IS_USED_ERROR;

public class UsernameUsedException extends Exception {
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public UsernameUsedException() {
        super(USERNAME_IS_USED_ERROR.toString());
        this.setStatusCode(USERNAME_IS_USED_ERROR.getCode());
    }
}
