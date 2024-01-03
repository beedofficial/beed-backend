package com.beed.controller;

import com.beed.model.request.NotifyAuctioneerRequest;
import com.beed.model.request.NotifyPreviousBidderRequest;
import com.beed.model.response.NotificationControllerResponse;
import com.beed.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.beed.model.constant.Error.*;
import static com.beed.model.constant.Success.*;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @PostMapping("/api/notify/auctioneer-for-new-bid")
    public ResponseEntity<NotificationControllerResponse> notifyAuctioneerForNewBid(@RequestBody NotifyAuctioneerRequest notifyAuctioneerRequest) {
        try {
            String firebaseMessageId = notificationService.notifyAuctioneer(
                    notifyAuctioneerRequest.getBidAmount(),
                    notifyAuctioneerRequest.getAuctioneerId(),
                    notifyAuctioneerRequest.getAuctionTitle()
            );

            NotificationControllerResponse controllerResponse = NotificationControllerResponse.builder()
                    .firebaseMessageId(firebaseMessageId)
                    .responseMessage(NOTIFY_AUCTIONEER_FOR_NEW_BID_SUCCESS.getDescription())
                    .responseCode(NOTIFY_AUCTIONEER_FOR_NEW_BID_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (FirebaseMessagingException e) {
            NotificationControllerResponse controllerResponse = NotificationControllerResponse.builder()
                    .responseMessage(NOTIFY_AUCTIONEER_FOR_NEW_BID_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(NOTIFY_AUCTIONEER_FOR_NEW_BID_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/notify/previous-bidder")
    public ResponseEntity<NotificationControllerResponse> notifyPreviousBidder(@RequestBody NotifyPreviousBidderRequest notifyPreviousBidderRequest) {
        try {
            String firebaseMessageId = notificationService.notifyPreviousBidder(
                    notifyPreviousBidderRequest.getBidAmount(),
                    notifyPreviousBidderRequest.getPreviousBidderId(),
                    notifyPreviousBidderRequest.getAuctionTitle()
            );

            NotificationControllerResponse controllerResponse = NotificationControllerResponse.builder()
                    .firebaseMessageId(firebaseMessageId)
                    .responseMessage(NOTIFY_PREVIOUS_BIDDER_SUCCESS.getDescription())
                    .responseCode(NOTIFY_PREVIOUS_BIDDER_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (FirebaseMessagingException e) {
            NotificationControllerResponse controllerResponse = NotificationControllerResponse.builder()
                    .responseMessage(NOTIFY_PREVIOUS_BIDDER_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(NOTIFY_PREVIOUS_BIDDER_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
