package com.beed.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "app_user", indexes = {@Index(name = "username_index", columnList = "username", unique = true)})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctioneer", cascade = CascadeType.ALL)
    private Set<Auction> auctions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bidder", cascade = CascadeType.ALL)
    private Set<Bid> bids;
}