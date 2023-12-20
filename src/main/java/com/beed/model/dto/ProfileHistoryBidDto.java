package com.beed.model.dto;

import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileHistoryBidDto {
    private Long id;
    private String auctionTitle;
    private Long auctionId;
    private Long amount;
    private OffsetDateTime date;
    private Boolean isDone;
    private String imageUrl;
}
