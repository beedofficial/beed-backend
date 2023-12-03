package com.beed.service;

import com.beed.model.entity.AppUser;
import com.beed.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final  AppUserService appUserService;

    public CustomUserDetailsService(AppUserRepository userRepository, AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserService.getUserByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("Username not found:" + username);
        return User.builder()
                        .username(user.get().getUsername())
                        .password(user.get().getEncryptedPassword())
                        .roles(user.get().getRole())
                        .build();
    }
}
