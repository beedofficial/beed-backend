package com.beed.model.response;

import com.beed.model.dto.AppUserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class isAuctionEndResponse {
    private int responseCode;
    private String responseMessage;
    private Boolean end;
}
