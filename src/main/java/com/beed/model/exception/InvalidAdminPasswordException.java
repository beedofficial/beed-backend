package com.beed.model.exception;

import static com.beed.model.constant.Error.INVALID_ADMIN_PASSWORD_ERROR;
import static com.beed.model.constant.Error.USERNAME_IS_USED_ERROR;

public class InvalidAdminPasswordException extends Exception {
    int statusCode;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public InvalidAdminPasswordException() {
        super(INVALID_ADMIN_PASSWORD_ERROR.toString());
        this.setStatusCode(INVALID_ADMIN_PASSWORD_ERROR.getCode());
    }
}
