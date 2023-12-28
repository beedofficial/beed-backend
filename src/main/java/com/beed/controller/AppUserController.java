package com.beed.controller;

import com.beed.model.constant.Role;
import com.beed.model.dto.AppUserDto;
import com.beed.model.response.GetUserInfoPageControllerResponse;
import com.beed.model.response.GetUserListResponse;
import com.beed.model.response.DeleteUserResponse;
import com.beed.model.response.UpdateUserInfoPageControllerResponse;
import com.beed.service.AppUserService;
import jakarta.annotation.security.RolesAllowed;
import com.beed.utility.AppUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.beed.model.constant.Error.*;
import static com.beed.model.constant.Success.*;

@RestController
public class AppUserController {
    @Autowired
    AppUserService appUserService;
    AppUserUtil appUserUtil;

    @GetMapping("api/appuser/get-user-info")
    public ResponseEntity<GetUserInfoPageControllerResponse> getUserInfoPage(Authentication authentication) {
        try {
            AppUserDto appUserDto = appUserService.getUserInfo(authentication.getName());

            GetUserInfoPageControllerResponse controllerResponse = GetUserInfoPageControllerResponse.builder()
                    .AppUser(appUserDto)
                    .responseMessage(GET_USER_INFO_PAGE_SUCCESS.getDescription())
                    .responseCode(GET_USER_INFO_PAGE_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {

            GetUserInfoPageControllerResponse controllerResponse = GetUserInfoPageControllerResponse.builder()
                    .responseMessage(GET_USER_INFO_PAGE_ERROR.getDescription())
                    .responseCode(GET_USER_INFO_PAGE_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed({Role.Admin})
    @GetMapping("/api/appuser/get-all-users")
    public ResponseEntity<GetUserListResponse> getFeedAuctionsPage(@RequestParam Integer page) {
        try {
            List<AppUserDto> appUserDtos = appUserService.getUserList(page);

            GetUserListResponse controllerResponse = GetUserListResponse.builder()
                    .userDtoList(appUserDtos)
                    .responseMessage(GET_ALL_USERS_INFO_SUCCESS.getDescription())
                    .responseCode(GET_ALL_USERS_INFO_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {
            GetUserListResponse controllerResponse = GetUserListResponse.builder()
                    .responseMessage(GET_ALL_USERS_INFO_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(GET_ALL_USERS_INFO_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/appuser/delete-user")
    public ResponseEntity<DeleteUserResponse> deleteUserAdminPage(@RequestParam Long id) {
        try {
            appUserService.deleteUserById(id);
            DeleteUserResponse response = DeleteUserResponse.builder()
                    .responseMessage(DELETE_USER_SUCCESS.getDescription())
                    .responseCode(DELETE_USER_SUCCESS.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            DeleteUserResponse response = DeleteUserResponse.builder()
                    .responseMessage(DELETE_USER_ERROR.getDescription())
                    .responseCode(DELETE_USER_ERROR.getCode())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/appuser/update-user-info")
    public ResponseEntity<UpdateUserInfoPageControllerResponse> updateUserInfoPage(@RequestBody AppUserDto userDto) {
        try {
            Long id = userDto.getId();
            AppUserDto oldUser = appUserService.getUserByID(id);
            AppUserDto userDtoFilled = appUserUtil.fillBlank(userDto, oldUser);

            appUserService.updateUserById(userDtoFilled);

            AppUserDto userUpdated = appUserService.getUserByID(id);

            UpdateUserInfoPageControllerResponse controllerResponse = UpdateUserInfoPageControllerResponse.builder()
                    .responseMessage(UPDATE_USER_INFO_SUCCESS.getDescription())
                    .responseCode(UPDATE_USER_INFO_SUCCESS.getCode())
                    .updatedUser(userUpdated)
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (Exception e) {

            UpdateUserInfoPageControllerResponse controllerResponse = UpdateUserInfoPageControllerResponse.builder()
                    .responseMessage(UPDATE_USER_INFO_ERROR.getDescription() + ":" + e.toString())
                    .responseCode(UPDATE_USER_INFO_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
