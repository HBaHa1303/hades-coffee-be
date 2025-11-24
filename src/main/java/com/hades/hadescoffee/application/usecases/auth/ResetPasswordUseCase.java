package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.presentation.dto.requests.auth.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class ResetPasswordUseCase {

    public void execute(ResetPasswordRequest request) {

    }
}
