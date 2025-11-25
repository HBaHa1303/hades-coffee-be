package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.user.UserSelfChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSelfChangePasswordUseCase {
    private final UsersRepository usersRepository;
    public void execute(UserSelfChangePasswordRequest request) {
        String currentPassword = request.getCurrentPassword();


        usersRepository.
    }
}
