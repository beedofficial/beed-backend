package com.beed.model.response;

import com.beed.model.dto.AppUserDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserInfoPageControllerResponse implements Serializable {
    private int responseCode;
    private String responseMessage;
    private AppUserDto updatedUser;
}
