package com.beed.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAuctionResponse {
    private int responseCode;
    private String responseMessage;
}