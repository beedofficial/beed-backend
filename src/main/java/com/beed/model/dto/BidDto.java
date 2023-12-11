package com.beed.model.dto;

import com.beed.model.entity.AppUser;
import com.beed.model.entity.Auction;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDto implements Serializable {
    private Long id;
    private Auction auction;
    private AppUser bidder;
    private Long amount;
    private OffsetDateTime date;
}
