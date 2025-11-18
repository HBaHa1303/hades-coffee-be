package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.application.utils.PageableUtil;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.domain.valueObjects.Password;
import com.hades.hadescoffee.presentation.dto.requests.user.ChangeUserPasswordRequest;
import com.hades.hadescoffee.presentation.dto.requests.user.FilterUsersRequest;
import com.hades.hadescoffee.presentation.dto.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class ChangeUserPasswordUseCase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(Long id, ChangeUserPasswordRequest request) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("user.not_found", id));

        String passwordHashed = passwordEncoder.encode(request.getNewPassword());
        user.changePassword(new Password(passwordHashed));
        usersRepository.save(user);
    }
}

