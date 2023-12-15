package com.beed.model.response;

import com.beed.model.dto.FeedPageAuctionDto;
import com.beed.model.dto.ProfileHistoryAuctionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProfileHistoryAuctionsControllerResponse {
    private int responseCode;
    private String responseMessage;
    private List<ProfileHistoryAuctionDto> profileHistoryAuctionList;
}
