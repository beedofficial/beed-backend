package com.beed.service;

import com.beed.model.dto.AuctionDto;
import static com.beed.utility.AuctionUtil.*;

import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.entity.Auction;
import com.beed.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;


    public AuctionDto getAuctionbyId(Long Id){
        Auction auction = auctionRepository.findById(Id).get();
        return convertAuctiontodto(auction);
    }

    public List<FeedPageAuctionDto> getFeedPageAuctionList(Integer page) {
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        return auctionRepository.getFeedPageAuctions(pageWithTenElements);
    }

}
