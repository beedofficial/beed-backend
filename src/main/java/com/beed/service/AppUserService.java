package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import com.beed.repository.AppUserRepository;
import com.beed.utility.AppUserUtil;
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

    @Transactional
    public void updateUser(AppUserDto userDto, String username) throws Exception {
        if (username != null) {
            Long userId = userRepository.getUserIdByUsername(username);
            userDto.setId(userId);
        }

        AppUserDto currentUserDto = this.getUserByID(userDto.getId());
        AppUserDto userDtoFilled = AppUserUtil.fillBlank(userDto, currentUserDto);

        userRepository.updateAppUser(userDtoFilled.getName(), userDtoFilled.getSurname()
                , userDtoFilled.getMail(), userDtoFilled.getPhone()
                , userDtoFilled.getUsername(),userDtoFilled.getId());
        if (userDtoFilled.getRate() != null) {
            userRepository.updateAppUserRate(userDtoFilled.getRate(), userDtoFilled.getId());
        }
        if (userDto.getProfilePhotoUrl() != null) {
            userRepository.updateAppUserPhoto(userDtoFilled.getProfilePhotoUrl(), userDtoFilled.getId());
        }
    }

    public boolean deleteUserById(Long id) throws Exception {
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }
    public void createNewUser(AppUserDto user, String password) throws Exception {
        userRepository.save(convertUserDtoToEntity(user, password));
    }

    public boolean isUsernameUsed(String username) {
        return userRepository.findAppUserByUsername(username).isPresent();
    }

    public String getUserDeviceToken(Long Id){
        return userRepository.getUserDeviceTokenById(Id);
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

    @Transactional
    public void updateUserRateById(Double newRate, Long id) throws Exception {
        userRepository.updateRating(newRate,id);
    }

}
