package com.beed.model.dto;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedPageAuctionDto implements Serializable {
    private Long id;
    private String title;
    private double minStartBid;
    private String imageUrl;
}
