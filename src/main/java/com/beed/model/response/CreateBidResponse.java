package com.beed.model.response;

import lombok.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBidResponse implements Serializable {
    private int responseCode;
    private String responseMessage;
    private Long previousBidderId;
}
