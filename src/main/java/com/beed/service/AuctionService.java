package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.AuctionDto;
import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.request.CreateAuctionRequest;
import com.beed.repository.AuctionRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.beed.utility.AppUserUtil.convertUserEntityToDto;
import static com.beed.utility.AuctionUtil.convertAuctiontoDto;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AppUserService appUserService;
    @Autowired
    BidService bidService;


    public boolean isAuctionEnd(long id) throws Exception{
        return auctionRepository.findById(id).get().getEndDate().compareTo(OffsetDateTime.now()) < 0;
    }
    public AppUserDto AuctionEndInfo(long id, String username) throws Exception{
        if (isAuctionEnd(id)){
            AppUserDto auctioneer = AuctionEndAuctioneerInfo(id);
            if (Objects.equals(auctioneer.getUsername(), username)){
                return bidService.higgestBidderInfo(id);
            }
            return auctioneer;
        }
        return null;
    }
    public AppUserDto AuctionEndAuctioneerInfo(long id) throws Exception{
        return convertUserEntityToDto(auctionRepository.findById(id).get().getAuctioneer());
    }
    public AuctionDto getAuctionbyId(Long Id){
        Auction auction = auctionRepository.findById(Id).get();
        return convertAuctiontoDto(auction);
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
    public Long createAuction(CreateAuctionRequest createAuctionRequest, String username) {
        Long userId = appUserService.getUserIdByUsername(username);

        AppUser appUser = appUserService.userRepository.findAppUserById(userId);

        int duration = createAuctionRequest.getDurationInfo();

        OffsetDateTime startDate = OffsetDateTime.now();
        OffsetDateTime endDate = startDate.plusMinutes((long) duration);

        Auction newAuction = Auction.builder()
                .description(createAuctionRequest.getDescription())
                .title(createAuctionRequest.getTitle())
                .minStartBid(createAuctionRequest.getMinStartBid())
                .startDate(startDate)
                .auctionImageUrl(createAuctionRequest.getImageUrl())
                .auctioneer(appUser)
                .endDate(endDate)
                .build();

        Auction savedAuction = auctionRepository.save(newAuction);

        return savedAuction.getId();
    }


    public AuctionDto getAuctionInfo(Long id){
        AuctionDto auctionDto = auctionRepository.getAuctionDtoById(id);
        return auctionDto;
    }

    public boolean deleteAuctionById(Long Id) throws Exception{
        auctionRepository.deleteById(Id);
        return auctionRepository.findById(Id).isEmpty();
    }


}
