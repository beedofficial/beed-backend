package com.beed.utility;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;

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
        newUser.setEncryptedPassword(password);
        return newUser;
    }

    public static List<AppUserDto> mapUserEntityToDto (List<AppUser> userList){
        return userList.stream().map(AppUserUtil::convertUserEntityToDto).toList();
    }
}
