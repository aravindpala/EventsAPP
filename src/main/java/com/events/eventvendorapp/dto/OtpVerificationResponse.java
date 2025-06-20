package com.events.eventvendorapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class OtpVerificationResponse {
    private String token;
    private String message;
    private String id;
}
