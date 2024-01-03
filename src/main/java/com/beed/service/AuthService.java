package com.beed.service;

import com.beed.model.constant.Role;
import com.beed.model.entity.AppUser;
import org.springframework.beans.factory.annotation.Value;
import com.beed.model.exception.InvalidAdminPasswordException;
import com.beed.model.exception.UsernameUsedException;
import com.beed.model.request.LoginRequest;
import com.beed.model.request.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${admin-register-password}")
    private String ADMIN_REGISTER_PASSWORD;

    public AuthService(AppUserService appUserService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest registerRequest) throws UsernameUsedException, Exception {
        if(appUserService.isUsernameUsed(registerRequest.getUsername()))
            throw new UsernameUsedException();

        if (registerRequest.getIsAdmin() && !registerRequest.getAdminPassword().equals(this.ADMIN_REGISTER_PASSWORD))
            throw new InvalidAdminPasswordException();

        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());

        String givenRole = registerRequest.getIsAdmin() ? Role.Admin : Role.User;

        AppUser newUser = AppUser.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .profilePhotoUrl(registerRequest.getProfilePhotoUrl())
                .username(registerRequest.getUsername())
                .mail(registerRequest.getMail())
                .role(givenRole)
                .phone(registerRequest.getPhone())
                .encryptedPassword(encryptedPassword)
                .deviceToken(registerRequest.getDeviceToken())
                .rate(0.0)
                .numOfRaters(0)
                .build();

        appUserService.saveAppUser(newUser);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String username = authentication.getName();
        AppUser appUser = appUserService.getUserByUsername(username).get();
        return jwtService.createToken(appUser);
    }


}
