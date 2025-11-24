package com.hades.hadescoffee.presentation.dto.requests.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfirmResetPasswordRequest {
    String email;
    String otp;
}
