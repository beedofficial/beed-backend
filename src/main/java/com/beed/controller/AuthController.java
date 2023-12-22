package com.beed.controller;

import com.beed.model.constant.Role;
import com.beed.model.exception.InvalidAdminPasswordException;
import com.beed.model.exception.UsernameUsedException;
import com.beed.model.request.LoginRequest;
import com.beed.model.request.RegisterRequest;
import com.beed.model.response.BaseControllerResponse;
import com.beed.model.response.LoginControllerResponse;
import com.beed.service.AuthService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.beed.model.constant.Error.AUTHENTICATION_ERROR;
import static com.beed.model.constant.Error.INVALID_USERNAME_OR_PASSWORD_ERROR;
import static com.beed.model.constant.Success.*;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/api/deneme")
    public ResponseEntity<String> a() {
        return new ResponseEntity<>("sdfsdfsdfsdfsdf", HttpStatus.OK);
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<BaseControllerResponse> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);

            BaseControllerResponse controllerResponse = BaseControllerResponse.builder()
                    .responseMessage(REGISTERED_SUCCESS.getDescription())
                    .responseCode(REGISTERED_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (UsernameUsedException e) {
            BaseControllerResponse controllerResponse = BaseControllerResponse.builder()
                    .responseMessage(e.getMessage())
                    .responseCode(e.getStatusCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.CONFLICT);

        } catch (InvalidAdminPasswordException e) {
            BaseControllerResponse controllerResponse = BaseControllerResponse.builder()
                    .responseMessage(e.getMessage())
                    .responseCode(e.getStatusCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            BaseControllerResponse controllerResponse = BaseControllerResponse.builder()
                    .responseMessage(e.toString())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginControllerResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);

            LoginControllerResponse controllerResponse = LoginControllerResponse.builder()
                    .token(token)
                    .responseMessage(LOGIN_SUCCESS.getDescription())
                    .responseCode(LOGIN_SUCCESS.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.OK);

        } catch (BadCredentialsException e){

            LoginControllerResponse controllerResponse = LoginControllerResponse.builder()
                    .responseMessage(INVALID_USERNAME_OR_PASSWORD_ERROR.getDescription())
                    .responseCode(INVALID_USERNAME_OR_PASSWORD_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LoginControllerResponse controllerResponse = LoginControllerResponse.builder()
                    .responseMessage(AUTHENTICATION_ERROR.getDescription())
                    .responseCode(AUTHENTICATION_ERROR.getCode())
                    .build();

            return new ResponseEntity<>(controllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed({Role.Admin})
    @GetMapping("api/auth/control-admin")
    public ResponseEntity<BaseControllerResponse> controlAdmin() {
        BaseControllerResponse controllerResponse = BaseControllerResponse.builder()
                .responseCode(ADMIN_CONTROL_SUCCESS.getCode())
                .responseMessage(ADMIN_CONTROL_SUCCESS.getDescription())
                .build();

        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }
}
