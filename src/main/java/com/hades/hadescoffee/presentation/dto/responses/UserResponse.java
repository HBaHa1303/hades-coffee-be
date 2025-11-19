package com.hades.hadescoffee.presentation.dto.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String email;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    Set<String> roles;
}
