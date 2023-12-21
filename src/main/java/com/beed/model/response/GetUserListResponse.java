package com.beed.model.response;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.FeedPageAuctionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserListResponse {
    private int responseCode;
    private String responseMessage;
    private List<AppUserDto> userDtoList;
}
