package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.exceptions.AValidationException;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserRoleEntity;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class SignUpUseCase {
    private final UsersRepository usersRepository;
    private final AUserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    public void execute (SignUpRequest request) {
        validatePassword(request.getPassword());

        usersRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new AConflictException("user.email_exists", user.getEmail());
                });
        Role role = rolesRepository.findByCode("USER")
                .orElseThrow(() -> new ANotFoundException("role.not_found", "user"));

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = mapper.signUpRequestToDomain(request, hashedPassword, role);

        usersRepository.save(user);
    }

    public void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new AValidationException("user.password_too_short", 8);
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new AValidationException("user.password_missing_uppercase");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new AValidationException("user.password_missing_lowercase");
        }

        if (!password.matches(".*\\d.*")) {
            throw new AValidationException("user.password_missing_digit");
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            throw new AValidationException("user.password_missing_special");
        }
    }
}
