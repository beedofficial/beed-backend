package com.beed.controller;

import com.beed.model.dto.AppUserDto;
import com.beed.model.response.GetUserInfoPageControllerResponse;
import com.beed.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import static com.beed.model.constant.Error.GET_USER_INFO_PAGE_ERROR;
import static com.beed.model.constant.Success.GET_USER_INFO_PAGE_SUCCESS;
@RestController
public class AppUserController {
    @Autowired
    AppUserService appUserService;

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
}
