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
    private Long minStartBid;
    private String imageUrl;
    private Long highestBid;
}
