package com.beed.model.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto implements Serializable{
    private long id;
    private String username;
    private String name;
    private String surname;
    private Double rate;
    private String mail;
    private String phone;
    private String imageUrl;
}
