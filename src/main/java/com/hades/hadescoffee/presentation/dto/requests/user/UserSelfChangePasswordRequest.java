package com.hades.hadescoffee.presentation.dto.requests.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSelfChangePasswordRequest {
    String newPassword;
    String currentPassword;
    String confirmPassword;
}
