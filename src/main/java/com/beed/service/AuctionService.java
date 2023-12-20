package com.beed.service;

import com.beed.model.dto.*;

import static com.beed.utility.AuctionUtil.*;

import com.beed.model.entity.Auction;
import com.beed.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AppUserService appUserService;


    public AuctionDto getAuctionbyId(Long Id){
        Auction auction = auctionRepository.findById(Id).get();
        return convertAuctiontodto(auction);
    }

    public List<FeedPageAuctionDto> getFeedPageAuctionList(Integer page) {
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        return auctionRepository.getFeedPageAuctions(pageWithTenElements);
    }

    public List<ProfileHistoryAuctionDto> getProfileHistoryAuctionList(String username, Integer page) {
        Long userId = appUserService.getUserIdByUsername(username);
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        List<ProfileHistoryAuctionDto> profileHistoryAuctionList = auctionRepository.getProfileHistoryAuctions(userId, pageWithTenElements);
        profileHistoryAuctionList.forEach(auction -> {
            auction.setDone(OffsetDateTime.now().isAfter(auction.getEndDate()));
        });

        return  profileHistoryAuctionList;
    }
  
    public List<FeedPageAuctionDto> getHotAuctionsPageAuctionList(Integer page) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime oneHourAgo = now.minus(Duration.ofHours(1));
        OffsetDateTime oneDayAgo = now.minus(Duration.ofDays(1));
        OffsetDateTime threeDaysAgo = now.minus(Duration.ofDays(3));
        OffsetDateTime oneWeekAgo = now.minus(Duration.ofDays(7));


        Pageable pageWithTenElements = PageRequest.of(page, 10);

        return auctionRepository.getHotAuctionsPageAuctions(
                oneHourAgo, oneDayAgo, threeDaysAgo, oneWeekAgo, pageWithTenElements);
    }

    public AuctionDto getAuctionInfo(Long id){
        AuctionDto auctionDto = auctionRepository.getAuctionDtoById(id);
        return auctionDto;
    }

}
