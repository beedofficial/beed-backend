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
    CREATE_AUCTION_SUCCESS(0,"Successfully created auction."),
    ADMIN_CONTROL_SUCCESS(0, "Your admin role has been accepted by system."),
    DELETE_USER_SUCCESS(0, "Successfully deleted user."),
    UPDATE_USER_INFO_SUCCESS(0, "Succesfully updated user info."),
    GET_AUCTION_END_INFO_SUCCESS(0, "Succesfully got user info."),
    DELETE_AUCTION_SUCCESS(0, "Successfully deleted auction."),
    IS_AUTION_END_SUCCESS(0, "Successfully checked end date."),
    BID_SUCCESS(0, "Successfully added new bid."),
    DELETE_BID_SUCCESS(0, "Successfully deleted bid."),
    GET_ALL_BIDS_INFO_SUCCESS(0, "Successfully retrieved all bids' information."),
    UPDATE_USER_RATE_SUCCESS(0, "Successfully updated rate.");

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
