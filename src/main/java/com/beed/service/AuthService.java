package com.beed.service;

import com.beed.model.entity.AppUser;
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

    private final String DEFAULT_ROLE = "USER";

    public AuthService(AppUserService appUserService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest registerRequest) throws UsernameUsedException, Exception {
        if(appUserService.isUsernameUsed(registerRequest.getUsername()))
            throw new UsernameUsedException();

        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());

        AppUser newUser = AppUser.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .mail(registerRequest.getMail())
                .role(DEFAULT_ROLE)
                .phone(registerRequest.getPhone())
                .encryptedPassword(encryptedPassword)
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
