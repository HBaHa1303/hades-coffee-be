package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.user.CreateUserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UsersRepository usersRepository;
    private final AUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public void execute(CreateUserRequest request) {
        usersRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {throw new AConflictException("user.email_exists", request.getEmail());});

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Set<UserRole> userRoles = request.getRoles().stream()
                .map(role -> new UserRole(null, role))
                .collect(Collectors.toSet());

        usersRepository.save(mapper.createRequestToDomain(request, hashedPassword, userRoles));
    }
}
