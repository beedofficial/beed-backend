package com.beed.model.dto;

import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileHistoryBidDto implements Serializable {
    private Long id;
    private String auctionTitle;
    private Long auctionId;
    private Long amount;
    private OffsetDateTime date;
    private String imageUrl;
    private OffsetDateTime auctionEndDate;
    private boolean isDone;

    public ProfileHistoryBidDto(Long id, String auctionTitle, Long auctionId, Long amount, OffsetDateTime date, String imageUrl, OffsetDateTime auctionEndDate) {
        this.id = id;
        this.auctionTitle = auctionTitle;
        this.auctionId = auctionId;
        this.amount = amount;
        this.date = date;
        this.imageUrl = imageUrl;
        this.auctionEndDate = auctionEndDate;
    }
}
