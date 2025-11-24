package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.presentation.dto.requests.auth.ConfirmResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class ConfirmResetPasswordUseCase {

    public void execute(ConfirmResetPasswordRequest request) {

    }
}
