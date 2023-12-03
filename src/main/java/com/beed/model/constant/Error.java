package com.beed.model.constant;

public enum Error {
    USERNAME_IS_USED_ERROR(1, "Username is used by someone else."),
    INVALID_USERNAME_OR_PASSWORD_ERROR(2, "Invalid username or password"),
    AUTHENTICATION_ERROR(3, "There is an error in authentication.");
    private final int code;
    private final String description;

    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
