package com.beed.model.response;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginControllerResponse implements Serializable {
    private int responseCode;
    private String responseMessage;
    private String token;
}
