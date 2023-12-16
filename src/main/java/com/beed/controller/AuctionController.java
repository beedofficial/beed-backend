package com.beed.controller;

import com.beed.model.dto.AuctionDto;
import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.response.*;
import com.beed.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.beed.model.response.GetAuctionViewControllerResponse;

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

    @GetMapping("/api/auction/get-hot-auctions-page-auctions")
    public ResponseEntity<GetHotAuctionsPageControllerResponse> getHotAuctionsPage(@RequestParam Integer page) {
        try {
            List<FeedPageAuctionDto> hotAuctionList = auctionService.getHotAuctionsPageAuctionList(page);

            GetHotAuctionsPageControllerResponse controllerResponse = GetHotAuctionsPageControllerResponse.builder()
                    .hotAuctionsPageAuctionList(hotAuctionList)
                    .responseMessage(GET_FEED_PAGE_AUCTIONS_SUCCESS.getDescription())
                    .responseCode(GET_FEED_PAGE_AUCTIONS_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetHotAuctionsPageControllerResponse controllerResponse = GetHotAuctionsPageControllerResponse.builder()
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

    @GetMapping("api/auction/get-auction-info-view")
    public ResponseEntity<GetAuctionViewControllerResponse> getAuctionInfoView(@RequestParam Long id){
        try {
            AuctionDto auctionDto = auctionService.getAuctionInfo(id);

            GetAuctionViewControllerResponse controllerResponse = GetAuctionViewControllerResponse.builder()
                    .auction(auctionDto)
                    .responseCode(GET_USER_INFO_PAGE_SUCCESS.getCode())
                    .responseMessage(GET_USER_INFO_PAGE_SUCCESS.getDescription())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        }catch (Exception e){
            GetAuctionViewControllerResponse controllerResponse = GetAuctionViewControllerResponse.builder()
                    .responseCode(GET_USER_INFO_PAGE_ERROR.getCode())
                    .responseMessage(GET_AUCTION_INFO_VIEW_ERROR.getDescription())
                    .build();

            return new ResponseEntity<>(controllerResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
