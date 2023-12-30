package com.beed.model.response;

import com.beed.model.dto.AppUserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWinnersInfoResponse {
    private int responseCode;
    private String responseMessage;
    private AppUserDto AppUser;
}

