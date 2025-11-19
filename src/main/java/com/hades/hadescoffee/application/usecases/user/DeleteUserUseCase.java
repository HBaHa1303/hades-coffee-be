package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UsersRepository usersRepository;

    public void execute(Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("user.not_found", id));
        usersRepository.delete(user);
    }
}
