package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.user.UpdateUserRolesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class UpdateUserRolesUseCase {
    private final UsersRepository usersRepository;

    public void execute(Long userId, UpdateUserRolesRequest request) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ANotFoundException("user.not_found", userId));

        user.changeRoles(request.getRoleCodes());

        usersRepository.save(user);
    }
}

