package com.beed.model.response;

import com.beed.model.dto.FeedPageAuctionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFeedPageAuctionsControllerResponse {
    private int responseCode;
    private String responseMessage;
    private List<FeedPageAuctionDto> feedPageAuctionList;
}