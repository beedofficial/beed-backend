package com.beed.service;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import com.beed.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try{
            AppUser user = userRepository.findById(id).get();
            return convertUserEntityToDto(user);
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public void deleteUserById(Long id) throws Exception {
        try{
            userRepository.deleteById(id);
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public void updateUserById(Long id, AppUserDto userDto) throws Exception {
        try{
            userRepository.updateAppUser(userDto.getUsername(), userDto.getName(), userDto.getSurname()
                    , userDto.getRate(), userDto.getMail(), userDto.getPhone(), userDto.getId());
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public void createNewUser(AppUserDto user, String password, String salt) throws Exception {
        try{
            userRepository.save(convertUserDtoToEntity(user, password, salt));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
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
