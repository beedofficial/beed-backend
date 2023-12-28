package com.beed.model.response;

import com.beed.model.dto.ProfileHistoryAuctionDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserResponse {
    private int responseCode;
    private String responseMessage;
}
