package com.beed.model.response;

import com.beed.model.dto.AppUserDto;
import com.beed.model.dto.AuctionDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserInfoPageControllerResponse{
    private int responseCode;
    private String responseMessage;
    private AppUserDto AppUser;
}
