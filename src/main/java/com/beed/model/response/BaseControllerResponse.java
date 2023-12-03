package com.beed.model.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseControllerResponse implements Serializable {
    private int responseCode;
    private String responseMessage;
}
