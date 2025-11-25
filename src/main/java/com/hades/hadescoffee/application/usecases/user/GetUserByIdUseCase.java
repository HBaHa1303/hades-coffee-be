package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUserByIdUseCase {
    private final UsersRepository usersRepository;
    private final AUserMapper mapper;

    public UserResponse execute(Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("user.not_found", id));
        return mapper.domainToResponse(user);
    }
}
