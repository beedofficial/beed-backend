package com.beed.model.request;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRateRequest {
    Long id;
    Double rate;
}
