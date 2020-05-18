package com.example.shop.frontend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReCaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}
