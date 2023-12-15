package com.beed.model.response;

import com.beed.model.dto.ProfileHistoryBidDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProfileHistoryBidsControllerResponse {
    private int responseCode;
    private String responseMessage;
    private List<ProfileHistoryBidDto> profileHistoryBidList;
}

