package com.beed.service;

import com.beed.model.dto.AuctionDto;
import com.beed.model.entity.Auction;
import com.beed.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    public static AuctionDto convertAuctiontodto (Auction auction) {

        return AuctionDto.builder()
                .id(auction.getId())
                .title(auction.getTitle())
                .description(auction.getDescription())
                .auctioneer(auction.getAuctioneer())
                .startDate(auction.getStartDate())
                .endDate(auction.getEndDate())
                .minStartBid(auction.getMinStartBid())
                .build();
    }

    public AuctionDto getAuctionbyId(Long Id){
        Auction auction = auctionRepository.findById(Id).get();
        return convertAuctiontodto(auction);
    }

}
