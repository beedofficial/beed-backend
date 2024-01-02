package com.beed.controller;

import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.exception.LowBidThanHighestBidException;
import com.beed.model.exception.LowBidThanMinStartBidException;
import com.beed.model.request.CreateBidRequest;
import com.beed.model.response.BaseControllerResponse;
import com.beed.model.response.CreateAuctionResponse;
import com.beed.model.response.GetProfileHistoryBidsControllerResponse;
import com.beed.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.beed.model.constant.Error.*;
import static com.beed.model.constant.Success.BID_SUCCESS;
import static com.beed.model.constant.Success.GET_PROFILE_HISTORY_BIDS_SUCCESS;

@RestController
public class BidController {
    @Autowired
    BidService bidService;

    @GetMapping("/api/bid/get-profile-history-bids")
    public ResponseEntity<GetProfileHistoryBidsControllerResponse> getFeedAuctionsPage(@RequestParam Integer page, Authentication authentication) {
        try {
            List<ProfileHistoryBidDto> bidList = bidService.getProfileHistoryBids(authentication.getName(), page);

            GetProfileHistoryBidsControllerResponse controllerResponse = GetProfileHistoryBidsControllerResponse.builder()
                    .profileHistoryBidList(bidList)
                    .responseMessage(GET_PROFILE_HISTORY_BIDS_SUCCESS.getDescription())
                    .responseCode(GET_PROFILE_HISTORY_BIDS_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetProfileHistoryBidsControllerResponse controllerResponse = GetProfileHistoryBidsControllerResponse.builder()
                    .responseMessage(GET_PROFILE_HISTORY_BIDS_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(GET_PROFILE_HISTORY_BIDS_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/bid/create-bid")
    public  ResponseEntity<CreateAuctionResponse> createBid(@RequestBody CreateBidRequest createBidRequest, Authentication authentication){
        try {
            bidService.addBid(createBidRequest, authentication.getName());

            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(BID_SUCCESS.getDescription())
                    .responseCode(BID_SUCCESS.getCode())
                    .build();
            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);


        }catch (LowBidThanHighestBidException e){
            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(BID_LOWER_THAN_HIGHEST_BID.getDescription())
                    .responseCode(BID_LOWER_THAN_HIGHEST_BID.getCode())
                    .build();
            return new ResponseEntity<>(controllerResponse, HttpStatus.BAD_REQUEST);
        }
        catch (LowBidThanMinStartBidException e){
            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(BID_LOWER_THAN_MIN_START_BID.getDescription())
                    .responseCode(BID_LOWER_THAN_MIN_START_BID.getCode())
                    .build();
            return new ResponseEntity<>(controllerResponse, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(BID_ERROR.getDescription())
                    .responseCode(BID_ERROR.getCode())
                    .build();
            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
