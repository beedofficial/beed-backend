package com.beed.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "auctioneer_id")
    private AppUser auctioneer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auction", cascade = CascadeType.ALL)
    private Set<Image> images;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @Column(name = "min_start_bid", nullable = false)
    private double minStartBid;
}
