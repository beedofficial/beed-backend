package com.beed.model.dto;

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
    private String auctioneerUsername;
    private Double auctioneerRate;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private double minStartBid;
}