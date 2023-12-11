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
    @Query("SELECT b FROM Bid b WHERE b.bidder = :bidder")
    List<Bid> findByBidder(@Param("bidder") AppUser bidder);

    @Query("SELECT b FROM Bid b WHERE b.auction = :auction")
    List<Bid> findByAuction(@Param("auction") Auction auction);

    @Query("SELECT b FROM Bid b WHERE b.auction = :auction ORDER BY b.amount DESC")
    Bid findTopByAuctionOrderByAmountDesc(@Param("auction") Auction auction);

}
