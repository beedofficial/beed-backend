package com.beed.repository;

import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.entity.Auction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long>, PagingAndSortingRepository<Auction, Long> {
    @Query("SELECT new com.beed.model.dto.FeedPageAuctionDto(" +
            "a.id, a.title, a.minStartBid) " +
            "FROM Auction a " +
            "ORDER BY a.startDate DESC")
    List<FeedPageAuctionDto> getFeedPageAuctions(Pageable pageable);

    @Query("SELECT new com.beed.model.dto.ProfileHistoryAuctionDto(" +
            "a.id, a.title, a.minStartBid, a.endDate) " +
            "FROM Auction a " +
            "WHERE a.auctioneer.id = :auctioneerId " +
            "ORDER BY a.startDate DESC")
    List<ProfileHistoryAuctionDto> getProfileHistoryAuctions(@Param("auctioneerId") Long auctioneerId, Pageable pageable);
}
