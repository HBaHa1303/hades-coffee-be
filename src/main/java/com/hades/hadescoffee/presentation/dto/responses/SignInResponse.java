package com.hades.hadescoffee.presentation.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SignInResponse {
    private String email;
    private String accessToken;
    private String refreshToken;
    String firstName;
    String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;
    private List<String> roles;
}
