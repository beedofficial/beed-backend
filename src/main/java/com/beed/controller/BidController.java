package com.beed.controller;

import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.exception.LowBidThanHighestBidException;
import com.beed.model.exception.LowBidThanMinStartBidException;
import com.beed.model.request.CreateBidRequest;
import com.beed.model.response.BaseControllerResponse;
import com.beed.model.response.CreateAuctionResponse;
import com.beed.model.constant.Role;
import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.response.DeleteBidResponse;
import com.beed.model.response.GetBidListResponse;
import com.beed.model.response.GetProfileHistoryBidsControllerResponse;
import com.beed.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.beed.model.constant.Error.*;
import static com.beed.model.constant.Success.BID_SUCCESS;
import static com.beed.model.constant.Success.GET_PROFILE_HISTORY_BIDS_SUCCESS;
import static com.beed.model.constant.Success.*;

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

    @RolesAllowed({Role.Admin})
    @DeleteMapping("/api/bid/delete-bid")
    public ResponseEntity<DeleteBidResponse> deleteBidAdmin(@RequestParam Long Id){
        try {
            bidService.deleteBidById(Id);
            DeleteBidResponse response = DeleteBidResponse.builder()
                    .responseMessage(DELETE_BID_SUCCESS.getDescription())
                    .responseCode(DELETE_BID_SUCCESS.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            DeleteBidResponse response = DeleteBidResponse.builder()
                    .responseMessage(DELETE_BID_ERROR.getDescription())
                    .responseCode(DELETE_BID_ERROR.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/bid/get-all-bids")
    public ResponseEntity<GetBidListResponse> getFeedBidPage(@RequestParam Integer page) {
        try {
            List<BidDto> bidDtos = bidService.getBidList(page);

            GetBidListResponse controllerResponse = GetBidListResponse.builder()
                    .bidDtoList(bidDtos)
                    .responseMessage(GET_ALL_BIDS_INFO_SUCCESS.getDescription())
                    .responseCode(GET_ALL_BIDS_INFO_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetBidListResponse controllerResponse = GetBidListResponse.builder()
                    .responseMessage(GET_ALL_BIDS_INFO_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(GET_ALL_BIDS_INFO_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
