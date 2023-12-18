package com.beed.model.dto;

import com.beed.model.entity.AppUser;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto implements Serializable{
    private Long id;
    private String title;
    private String description;
    private AppUser auctioneer;
    private String imageUrl;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private double minStartBid;
}