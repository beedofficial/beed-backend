package com.beed.model.response;

import com.beed.model.dto.AuctionDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAuctionViewControllerResponse {
    private int responseCode;
    private String responseMessage;
    private AuctionDto auction;
}
