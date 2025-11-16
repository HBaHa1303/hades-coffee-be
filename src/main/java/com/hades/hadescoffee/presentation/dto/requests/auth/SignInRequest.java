package com.hades.hadescoffee.presentation.dto.requests.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {
    String email;
    String password;
}
