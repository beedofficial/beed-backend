package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import com.beed.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.beed.utility.AppUserUtil.*;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository userRepository;

    public List<AppUserDto> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        return mapUserEntityToDto(users);
    }

    public AppUserDto getUserByID(Long id) throws Exception {
        AppUser user = userRepository.findById(id).get();
        return convertUserEntityToDto(user);
    }

    public void deleteUserById(Long id) throws Exception {
        userRepository.deleteById(id);
    }
    @Transactional
    public void updateUserById(AppUserDto userDto) throws Exception {
        userRepository.updateAppUser(userDto.getName(), userDto.getSurname()
                , userDto.getMail(), userDto.getPhone()
                , userDto.getUsername(),userDto.getId());
        if (userDto.getRate() != null) {
            userRepository.updateAppUserRate(userDto.getRate(),userDto.getId());
        }
        if (userDto.getProfilePhotoUrl() != null) {
            userRepository.updateAppUserPhoto(userDto.getProfilePhotoUrl(),userDto.getId());
        }

    }

    public void createNewUser(AppUserDto user, String password) throws Exception {
        userRepository.save(convertUserDtoToEntity(user, password));
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

    public Long getUserIdByUsername(String username) {
        return userRepository.getUserIdByUsername(username);
    }

    public AppUserDto getUserInfo(String username) {
        AppUserDto appUserDto = userRepository.getAppUserDtoByUsername(username);
        return appUserDto;
    }

    public List<AppUserDto> getUserList(Integer page) throws Exception {
        Pageable pageWithTenElements = PageRequest.of(page, 10);
        return userRepository.getUsersInfos(pageWithTenElements);
    }
}
