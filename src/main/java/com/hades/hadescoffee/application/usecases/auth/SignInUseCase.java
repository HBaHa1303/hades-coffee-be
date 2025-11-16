package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.presentation.dto.requests.auth.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignInUseCase {
    public void execute(SignInRequest request) {

    }
}
