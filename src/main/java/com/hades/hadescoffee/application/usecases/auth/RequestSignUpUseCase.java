package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.presentation.dto.requests.auth.ConfirmSignUpRequest;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestSignUpUseCase {

    public void execute(SignUpRequest request) {
        validatePassword(request.getPassword());

        usersRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new AConflictException("user.email_exists", user.getEmail());
    }
}
