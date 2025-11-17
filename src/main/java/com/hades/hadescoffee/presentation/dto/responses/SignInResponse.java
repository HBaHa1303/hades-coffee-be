package com.hades.hadescoffee.presentation.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SignInResponse {
    private String email;
    private String accessToken;
    private String refreshToken;
    private List<String> roles;
}
