package com.beed.controller;

import com.beed.model.constant.Role;
import com.beed.model.dto.AppUserDto;
import com.beed.model.response.*;
import jakarta.annotation.security.RolesAllowed;
import com.beed.model.dto.AuctionDto;
import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.request.CreateAuctionRequest;
import com.beed.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.beed.utility.AppUserUtil;

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

    @GetMapping("/api/auction/get-winner-and-auctioneer")
    public ResponseEntity<GetWinnersInfoResponse> getWinnersInfo(@RequestParam Long id, Authentication authentication){
        try{
            AppUserDto userInfo = auctionService.AuctionEndInfo(id, authentication.getName());
            GetWinnersInfoResponse infoResponse = GetWinnersInfoResponse.builder()
                    .AppUser(userInfo)
                    .responseCode(GET_AUCTION_END_INFO_SUCCESS.getCode())
                    .responseMessage(GET_AUCTION_END_INFO_SUCCESS.getDescription()).build();

            return  new ResponseEntity<>(infoResponse, HttpStatus.OK);
        } catch (Exception e){
            GetWinnersInfoResponse infoResponse = GetWinnersInfoResponse.builder()
                    .responseCode(GET_AUCTION_END_INFO_ERROR.getCode())
                    .responseMessage(GET_AUCTION_END_INFO_ERROR.getDescription()).build();

            return new ResponseEntity<>(infoResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/auction/end-date")
    public ResponseEntity<isAuctionEndResponse> isAuctionEnd(@RequestParam Long id, Authentication authentication){
        try{
            isAuctionEndResponse response = isAuctionEndResponse.builder()
                    .end(auctionService.isAuctionEnd(id, authentication.getName()))
                    .responseCode(IS_AUTION_END_SUCCESS.getCode())
                    .responseMessage(IS_AUTION_END_SUCCESS.getDescription()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            isAuctionEndResponse response = isAuctionEndResponse.builder()
                    .responseCode(IS_AUCTION_END_ERROR.getCode())
                    .responseMessage(IS_AUCTION_END_ERROR.getDescription()).build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<GetAuctionViewControllerResponse> getAuctionInfoView(@RequestParam Long id) {
        try {
            AuctionDto auctionDto = auctionService.getAuctionInfo(id);

            GetAuctionViewControllerResponse controllerResponse = GetAuctionViewControllerResponse.builder()
                    .auction(auctionDto)
                    .responseCode(GET_AUCTION_INFO_VIEW_SUCCESS.getCode())
                    .responseMessage(GET_AUCTION_INFO_VIEW_SUCCESS.getDescription())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
        } catch (Exception e) {
            GetAuctionViewControllerResponse controllerResponse = GetAuctionViewControllerResponse.builder()
                    .responseCode(GET_AUCTION_INFO_VIEW_ERROR.getCode())
                    .responseMessage(GET_AUCTION_INFO_VIEW_ERROR.getDescription() + ":" + e.toString())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/api/auction/create-auction")
    public ResponseEntity<CreateAuctionResponse> createAuction(@RequestBody CreateAuctionRequest createAuctionRequest, Authentication authentication) {
        try {
            Long auctionId = auctionService.createAuction(createAuctionRequest, authentication.getName());


            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(CREATE_AUCTION_SUCCESS.getDescription())
                    .responseCode(CREATE_AUCTION_SUCCESS.getCode())
                    .auctionId(auctionId)
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
        } catch (Exception e) {
            CreateAuctionResponse controllerResponse = CreateAuctionResponse.builder()
                    .responseMessage(CREATE_AUCTION_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(CREATE_AUCTION_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed({Role.Admin})
    @DeleteMapping("/api/auction/delete-auction")
    public ResponseEntity<DeleteAuctionResponse> deleteAuctionAdmin(@RequestParam Long Id){
        try {
            auctionService.deleteAuctionById(Id);
            DeleteAuctionResponse response = DeleteAuctionResponse.builder()
                    .responseMessage(DELETE_AUCTION_SUCCESS.getDescription())
                    .responseCode(DELETE_AUCTION_SUCCESS.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            DeleteAuctionResponse response = DeleteAuctionResponse.builder()
                    .responseMessage(DELETE_AUCTION_ERROR.getDescription())
                    .responseCode(DELETE_AUCTION_ERROR.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
