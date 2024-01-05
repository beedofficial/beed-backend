package com.beed.utility;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import com.beed.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppUserUtil {
    public static AppUserDto convertUserEntityToDto(AppUser user){
        return AppUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .rate(user.getRate())
                .mail(user.getMail())
                .phone(user.getPhone())
                .profilePhotoUrl(user.getProfilePhotoUrl())
                .build();
    }

    public static AppUser convertUserDtoToEntity(AppUserDto user, String password){
        AppUser newUser = new AppUser();
        newUser.setUsername(user.getUsername());
        newUser.setMail(user.getMail());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setRate(user.getRate());
        newUser.setPhone(user.getPhone());
        newUser.setProfilePhotoUrl(user.getProfilePhotoUrl());
        newUser.setEncryptedPassword(password);
        return newUser;
    }

    public static List<AppUserDto> mapUserEntityToDto (List<AppUser> userList){
        return userList.stream().map(AppUserUtil::convertUserEntityToDto).toList();
    }

    public static  AppUserDto fillBlank(AppUserDto userDto, AppUserDto oldUser){
        if(userDto.getName() == null){
            userDto.setName(oldUser.getName());
        }
        if(userDto.getSurname() == null){
            userDto.setSurname(oldUser.getSurname());
        }
        if(userDto.getUsername() == null){
            userDto.setUsername(oldUser.getUsername());
        }
        if(userDto.getMail() == null){
            userDto.setMail(oldUser.getMail());
        }
        if(userDto.getPhone() == null){
            userDto.setPhone(oldUser.getPhone());
        }
        if(userDto.getRate() == null){
            userDto.setRate(oldUser.getRate());
        }
        return userDto;
    }
}
