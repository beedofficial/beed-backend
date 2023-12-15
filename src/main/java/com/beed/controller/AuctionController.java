package com.beed.controller;

import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.response.GetFeedPageAuctionsControllerResponse;
import com.beed.model.response.GetProfileHistoryAuctionsControllerResponse;
import com.beed.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.beed.model.constant.Error.*;
import static com.beed.model.constant.Success.*;

@RestController
public class AuctionController {
    @Autowired
    AuctionService auctionService;
    @GetMapping("/api/auction/get-feed-page-auctions")
    public ResponseEntity<GetFeedPageAuctionsControllerResponse> getFeedAuctionsPage(@RequestParam Integer page) {
        try {
            List<FeedPageAuctionDto> auctionList = auctionService.getFeedPageAuctionList(page);

            GetFeedPageAuctionsControllerResponse controllerResponse = GetFeedPageAuctionsControllerResponse.builder()
                    .feedPageAuctionList(auctionList)
                    .responseMessage(GET_FEED_PAGE_AUCTIONS_SUCCESS.getDescription())
                    .responseCode(GET_FEED_PAGE_AUCTIONS_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetFeedPageAuctionsControllerResponse controllerResponse = GetFeedPageAuctionsControllerResponse.builder()
                    .responseMessage(GET_FEED_PAGE_AUCTIONS_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(GET_FEED_PAGE_AUCTIONS_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/auction/get-profile-history-auctions")
    public ResponseEntity<GetProfileHistoryAuctionsControllerResponse> getFeedAuctionsPage(@RequestParam Integer page, Authentication authentication) {
        try {
            List<ProfileHistoryAuctionDto> auctionList = auctionService.getProfileHistoryAuctionList(authentication.getName(), page);

            GetProfileHistoryAuctionsControllerResponse controllerResponse = GetProfileHistoryAuctionsControllerResponse.builder()
                    .profileHistoryAuctionList(auctionList)
                    .responseMessage(GET_PROFILE_HISTORY_AUCTIONS_SUCCESS.getDescription())
                    .responseCode(GET_PROFILE_HISTORY_AUCTIONS_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetProfileHistoryAuctionsControllerResponse controllerResponse = GetProfileHistoryAuctionsControllerResponse.builder()
                    .responseMessage(GET_PROFILE_HISTORY_AUCTIONS_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(GET_PROFILE_HISTORY_AUCTIONS_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
