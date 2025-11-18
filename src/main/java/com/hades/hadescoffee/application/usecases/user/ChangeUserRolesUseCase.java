package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.user.ChangeUserRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class ChangeUserRolesUseCase {
    private final UsersRepository usersRepository;

    public void execute(Long id, ChangeUserRoleRequest request) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("user.not_found", id));

        user.changeRoles(request.getRoleCodes());
        usersRepository.save(user);
    }
}

