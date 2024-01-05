package com.beed.repository;

import com.beed.model.dto.*;
import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long>, PagingAndSortingRepository<Bid, Long> {
    @Query("SELECT b FROM Bid b WHERE b.bidder.id = :bidderId")
    List<Bid> findByBidder(@Param("bidderId") long bidderId);

    @Query("SELECT b.bidder FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC LIMIT 1")
    AppUser findHighestBidder(@Param("auctionId") long auctionId);

    @Query("SELECT b.bidder.id FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC LIMIT 1")
    Long getHighestBidderId(@Param("auctionId") Long auctionId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId")
    List<Bid> findByAuction(@Param("auctionId") long auctionId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC")
    Bid findTopByAuctionOrderByAmountDesc(@Param("auctionId") long auctionId);

    @Query("SELECT b.amount FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC LIMIT 1")
    Long getHighestBidValue(@Param("auctionId") long auctionId);

    @Query("SELECT new com.beed.model.dto.ProfileHistoryBidDto(" +
            "a.id, a.auction.title, a.auction.id, a.amount, a.date, a.auction.auctionImageUrl, a.auction.endDate) " +
            "FROM Bid a " +
            "WHERE a.bidder.id = :bidderId " +
            "ORDER BY a.date DESC")
    List<ProfileHistoryBidDto> getProfileHistoryBids(@Param("bidderId") Long bidderId, Pageable pageable);

    @Query("SELECT new com.beed.model.dto.AdminBidDto(" +
            "a.id, a.auction.title, a.bidder.username, a.amount, a.date, a.auction.auctionImageUrl) " +
            "FROM Bid a ")
    List<AdminBidDto> getBidsInfos(Pageable pageable);
}
