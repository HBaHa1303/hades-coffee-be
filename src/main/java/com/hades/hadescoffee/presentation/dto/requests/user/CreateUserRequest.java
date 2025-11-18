package com.hades.hadescoffee.presentation.dto.requests.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    String email;
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    Set<String> roles;
}

