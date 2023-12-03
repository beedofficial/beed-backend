package com.beed.model.constant;

public enum Success {
    REGISTERED_SUCCESS(0, "Successfully registered"),
    LOGIN_SUCCESS(0, "Successfully logged in.");
    private final int code;
    private final String description;

    private Success(int code, String description) {
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
