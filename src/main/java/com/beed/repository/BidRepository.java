package com.beed.repository;

import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import com.beed.model.entity.Bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
    @Query("SELECT b FROM Bid b WHERE b.bidder.id = :bidderId")
    List<Bid> findByBidder(@Param("bidderId") long bidderId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId")
    List<Bid> findByAuction(@Param("auctionId") long auctionId);

    @Query("SELECT b FROM Bid b WHERE b.auction.id = :auctionId ORDER BY b.amount DESC")
    Bid findTopByAuctionOrderByAmountDesc(@Param("auctionId") long auctionId);

}
