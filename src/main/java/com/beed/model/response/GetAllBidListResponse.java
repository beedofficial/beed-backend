package com.beed.model.response;

import com.beed.model.dto.AdminBidDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllBidListResponse {
    private int responseCode;
    private String responseMessage;
    private List<AdminBidDto> adminBidDtoList;
}
