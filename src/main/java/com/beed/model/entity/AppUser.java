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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name="profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "rate")
    private Integer numOfRaters;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctioneer", cascade = CascadeType.ALL)
    private Set<Auction> auctions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bidder", cascade = CascadeType.ALL)
    private Set<Bid> bids;
}
