package com.beed.repository;

import com.beed.model.dto.AuctionDto;
import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.entity.Auction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
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

    @Query("SELECT new com.beed.model.dto.FeedPageAuctionDto(" +
            "a.id, a.title, a.minStartBid) " +
            "FROM Auction a " +
            "LEFT JOIN Bid b ON a.id = b.auction.id " +
            "GROUP BY a.id " +
            "ORDER BY " +
            "SUM(CASE WHEN b.date >= :oneHourAgo THEN 1 ELSE 0 END) * 1.0 + " +
            "SUM(CASE WHEN b.date >= :oneDayAgo THEN 1 ELSE 0 END) * 0.5 + " +
            "SUM(CASE WHEN b.date >= :threeDaysAgo THEN 1 ELSE 0 END) * 0.3 + " +
            "SUM(CASE WHEN b.date >= :oneWeekAgo THEN 1 ELSE 0 END) * 0.2 DESC")
    List<FeedPageAuctionDto> getHotAuctionsPageAuctions(
            @Param("oneHourAgo") OffsetDateTime oneHourAgo,
            @Param("oneDayAgo") OffsetDateTime oneDayAgo,
            @Param("threeDaysAgo") OffsetDateTime threeDaysAgo,
            @Param("oneWeekAgo") OffsetDateTime oneWeekAgo,
            Pageable pageable);

    @Query("SELECT new com.beed.model.dto.AuctionDto (" +
           "a.id, a.title, a.description, a.auctioneer.username,a.auctioneer.rate, a.startDate, a.endDate, a.minStartBid) " +
            "FROM Auction a " +
            "WHERE a.id = :id ")
    AuctionDto getAuctionDtoById(@Param("id") Long id);
}
