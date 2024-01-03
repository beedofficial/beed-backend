package com.beed.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationControllerResponse {
    private int responseCode;
    private String responseMessage;
    private String firebaseMessageId;
}
