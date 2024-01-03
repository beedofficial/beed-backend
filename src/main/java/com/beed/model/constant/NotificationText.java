package com.beed.model.constant;

public enum NotificationText {
    NOTIFY_AUCTIONEER("New bid for your auction!", " has new bid with amount "),
    NOTIFY_PREVIOUS_BIDDER("Your bid has been outbid!", " has new bid with amount ");

    private final String title;
    private final String body;

    private NotificationText(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String constructBody(String auctionName, Long auctionAmount) {
        return auctionName + this.body + auctionAmount.toString() + Currency.TURKISH_LIRA.getSymbol() + ".";
    }
}
