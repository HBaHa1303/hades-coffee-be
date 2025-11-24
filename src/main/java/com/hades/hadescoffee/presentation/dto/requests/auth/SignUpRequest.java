package com.hades.hadescoffee.presentation.dto.requests.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    String email;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    LocalDate dateOfBirth;
    String firstName;
    String lastName;
}
