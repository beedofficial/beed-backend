package com.beed.model.dto;

import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileHistoryAuctionDto implements Serializable {
    private Long id;
    private String title;
    private double minStartBid;
    private OffsetDateTime endDate;
    private boolean isDone;
    private String imageUrl;
    private Long highestBid;

    public ProfileHistoryAuctionDto(Long id, String title, double minStartBid, OffsetDateTime endDate, String imageUrl, Long highestBid) {
        this.id = id;
        this.title = title;
        this.minStartBid = minStartBid;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.highestBid = highestBid;
    }
}

