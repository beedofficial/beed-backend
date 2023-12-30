package com.beed.model.constant;

public enum Error {
    USERNAME_IS_USED_ERROR(1, "Username is used by someone else."),
    INVALID_USERNAME_OR_PASSWORD_ERROR(2, "Invalid username or password"),
    AUTHENTICATION_ERROR(3, "There is an error in authentication."),
    GET_FEED_PAGE_AUCTIONS_ERROR(4, "Failed to retrieve auctions"),
    GET_PROFILE_HISTORY_AUCTIONS_ERROR(5, "Failed to retrieve auctions for profile history."),
    GET_PROFILE_HISTORY_BIDS_ERROR(6, "Failed to retrieve bids for profile history."),
    GET_USER_INFO_PAGE_ERROR(7, "Failed to retrieve user for user info page."),
    GET_AUCTION_INFO_VIEW_ERROR(8, "Failed to retrieve user for user info page."),
    CREATE_AUCTION_ERROR(9, "Failed to create auction."),
    GET_ALL_USERS_INFO_ERROR(10,"Failed to retrieve users"),

    INVALID_ADMIN_PASSWORD_ERROR(11, "You can not register as a admin because the admin password you entered is invalid."),

    UPDATE_USER_INFO_ERROR(12, "Failed to update user info."),
    DELETE_USER_ERROR(13, "Failed to delete user."),
    DELETE_AUCTION_ERROR(14, "Failed to delete auction.");

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
