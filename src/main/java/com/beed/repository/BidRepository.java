package com.beed.repository;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.BidDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import com.beed.model.dto.ProfileHistoryBidDto;
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
    AppUser findHiggestBidder(@Param("auctionId") long auctionId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId")
    List<Bid> findByAuction(@Param("auctionId") long auctionId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC")
    Bid findTopByAuctionOrderByAmountDesc(@Param("auctionId") long auctionId);

    @Query("SELECT new com.beed.model.dto.ProfileHistoryBidDto(" +
            "a.id, a.auction.title, a.auction.id, a.amount, a.date) " +
            "FROM Bid a " +
            "WHERE a.bidder.id = :bidderId " +
            "ORDER BY a.date DESC")
    List<ProfileHistoryBidDto> getProfileHistoryBids(@Param("bidderId") Long bidderId, Pageable pageable);

    @Query("SELECT new com.beed.model.dto.BidDto(" +
            "a.id, a.auction.auctionImageUrl, a.auction.title, a.bidder, a.amount, a.date) " +
            "FROM Bid a ")
    List<BidDto> getBidsInfos(Pageable pageable);
}
