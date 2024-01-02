package com.beed.controller;

import com.beed.model.constant.Role;
import com.beed.model.dto.ProfileHistoryBidDto;
import com.beed.model.response.DeleteBidResponse;
import com.beed.model.response.GetProfileHistoryBidsControllerResponse;
import com.beed.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.beed.model.constant.Error.DELETE_BID_ERROR;
import static com.beed.model.constant.Error.GET_PROFILE_HISTORY_BIDS_ERROR;
import static com.beed.model.constant.Success.DELETE_BID_SUCCESS;
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
}
