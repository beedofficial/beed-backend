package com.beed.model.response;

import com.beed.model.dto.BidDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBidListResponse {
    private int responseCode;
    private String responseMessage;
    private List<BidDto> bidDtoList;
}
