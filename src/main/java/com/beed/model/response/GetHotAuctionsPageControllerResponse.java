package com.beed.model.response;

import com.beed.model.dto.FeedPageAuctionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetHotAuctionsPageControllerResponse {
    private int responseCode;
    private String responseMessage;
    private List<FeedPageAuctionDto> hotAuctionsPageAuctionList;
}
