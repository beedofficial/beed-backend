package com.beed.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NotifyPreviousBidderRequest implements Serializable {
    private String auctionTitle;
    private Long bidAmount;
    private Long previousBidderId;
}
