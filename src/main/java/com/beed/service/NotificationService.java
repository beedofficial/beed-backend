package com.beed.service;

import com.beed.model.constant.NotificationText;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    public NotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    /**
     * Triggers push notification to be sent auctioneer whose auction got new bid.
     * @param deviceToken Unique token of mobile device of auctioneer.
     * @param bidAmount Amount of new bid for related auction.
     * @param auctionName Name of related auction.
     * @return firebase message id
     * @throws FirebaseMessagingException if failed
     */
    public String notifyAuctioneer(String deviceToken, Double bidAmount, String auctionName) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(NotificationText.NOTIFY_AUCTIONEER.getTitle())
                        .setBody(NotificationText.NOTIFY_AUCTIONEER.constructBody(auctionName, bidAmount))
                        .build())
                .setToken(deviceToken)
                .build();

        return firebaseMessaging.send(message);
    }

    /**
     * Triggers push notification to be sent the bidder who has previous highest bid for related auction.
     * @param deviceToken Unique token of mobile device of  the bidder who has previous highest bid for related auction.
     * @param newBidAmount Amount of new bid for related auction.
     * @param auctionName Name of related auction.
     * @return firebase message id
     * @throws FirebaseMessagingException if failed
     */
    public String notifyPreviousBidder(String deviceToken, Double newBidAmount, String auctionName) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(NotificationText.NOTIFY_PREVIOUS_BIDDER.getTitle())
                        .setBody(NotificationText.NOTIFY_PREVIOUS_BIDDER.constructBody(auctionName, newBidAmount))
                        .build())
                .setToken(deviceToken)
                .build();

        return firebaseMessaging.send(message);
    }
}
