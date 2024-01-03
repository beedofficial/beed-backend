package com.beed.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
    private String profilePhotoUrl;
    private String mail;
    private String phone;
    private String password;
    private String deviceToken;
    private Boolean isAdmin = false;
    private String adminPassword;
}
