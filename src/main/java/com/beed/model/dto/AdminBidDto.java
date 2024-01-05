package com.beed.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminBidDto implements Serializable {
    private Long id;
    private String auctionTitle;
    private String bidderUsername;
    private Long amount;
    private OffsetDateTime date;
    private String imageUrl;

    public AdminBidDto(Long id, String auctionTitle, Long amount, OffsetDateTime date, String imageUrl) {
        this.id = id;
        this.auctionTitle = auctionTitle;
        this.amount = amount;
        this.date = date;
        this.imageUrl = imageUrl;
    }
}
