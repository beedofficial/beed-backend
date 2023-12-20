package com.beed.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAuctionRequest {
    private String title;
    private String description;
    private double minStartBid;
    private int durationInfo;
    private String imageUrl;
}
