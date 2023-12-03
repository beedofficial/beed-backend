package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import com.beed.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository userRepository;

    public static AppUserDto convertUserEntityToDto(AppUser user){
        return AppUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .rate(user.getRate())
                .mail(user.getMail())
                .phone(user.getPhone())
                .build();
    }

    public static List<AppUserDto> mapUserEntityToDto (List<AppUser> userList){
        return userList.stream().map(AppUserService::convertUserEntityToDto).toList();
    }

    public List<AppUserDto> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        return mapUserEntityToDto(users);
    }

    public AppUserDto getUserByID(Long id) {
        AppUser user = userRepository.findById(id).get();
        return convertUserEntityToDto(user);
    }

    public boolean isUsernameUsed(String username) {
        return userRepository.findAppUserByUsername(username).isPresent();
    }

    public Optional<AppUser> getUserByUsername(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    public void saveAppUser(AppUser appUser) {
        userRepository.save(appUser);
    }
}
