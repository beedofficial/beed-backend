package com.beed.model.constant;

public enum Success {
    REGISTERED_SUCCESS(0, "Successfully registered"),
    LOGIN_SUCCESS(0, "Successfully logged in."),
    GET_FEED_PAGE_AUCTIONS_SUCCESS(0, "Successfully retrieved feed page auctions."),
    GET_ALL_USERS_INFO_SUCCESS(0, "Successfully retrieved all users' information."),
    GET_PROFILE_HISTORY_AUCTIONS_SUCCESS(0, "Successfully retrieved profile history auctions."),
    GET_PROFILE_HISTORY_BIDS_SUCCESS(0, "Successfully retrieved profile history bids."),
    GET_USER_INFO_PAGE_SUCCESS(0, "Successfully retrieved user info profile."),
    GET_AUCTION_INFO_VIEW_SUCCESS(0, "Successfully retrieved auction info view."),
    CREATE_AUCTION_SUCCESS(0,"Successfully created auction.");


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
