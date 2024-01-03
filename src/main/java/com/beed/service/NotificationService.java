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
    private final AppUserService appUserService;

    public NotificationService(FirebaseMessaging firebaseMessaging, AuctionService auctionService, AppUserService appUserService) {
        this.firebaseMessaging = firebaseMessaging;
        this.appUserService = appUserService;
    }

    public String notifyAuctioneer(Long bidAmount, Long auctioneerId, String auctionTitle) throws FirebaseMessagingException {
        String deviceTokenOfAuctioneer = appUserService.getUserDeviceToken(auctioneerId);

        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(NotificationText.NOTIFY_AUCTIONEER.getTitle())
                        .setBody(NotificationText.NOTIFY_AUCTIONEER.constructBody(auctionTitle, bidAmount))
                        .build())
                .setToken(deviceTokenOfAuctioneer)
                .build();

        return firebaseMessaging.send(message);
    }

    public String notifyPreviousBidder(Long newBidAmount, Long previousBidderId, String auctionTitle) throws FirebaseMessagingException {
        String deviceTokenOfPreviousBidder = appUserService.getUserDeviceToken(previousBidderId);

        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(NotificationText.NOTIFY_PREVIOUS_BIDDER.getTitle())
                        .setBody(NotificationText.NOTIFY_PREVIOUS_BIDDER.constructBody(auctionTitle, newBidAmount))
                        .build())
                .setToken(deviceTokenOfPreviousBidder)
                .build();

        return firebaseMessaging.send(message);
    }
}
