package com.beed.utility;

import com.beed.model.dto.AuctionDto;
import com.beed.model.entity.Auction;

public class AuctionUtil{
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
    public static Auction convertAuctiondtoAuction(AuctionDto auctionDto){
        Auction newAuction = new Auction();
        newAuction.setTitle(auctionDto.getTitle());
        newAuction.setDescription(auctionDto.getDescription());
        newAuction.setAuctioneer(auctionDto.getAuctioneer());
        newAuction.setStartDate(auctionDto.getStartDate());
        newAuction.setEndDate(auctionDto.getEndDate());
        newAuction.setMinStartBid(auctionDto.getMinStartBid());
        return newAuction;
    }



}