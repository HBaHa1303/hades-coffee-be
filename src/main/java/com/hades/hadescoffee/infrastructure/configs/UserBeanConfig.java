package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.application.usecases.user.*;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserBeanConfig {

    private final UsersRepository usersRepository;
    private final AUserMapper mapper;

    @Bean
    public CreateUserUseCase createUserUseCase(PasswordEncoder passwordEncoder) {
        return new CreateUserUseCase(usersRepository, mapper, passwordEncoder);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase() {
        return new DeleteUserUseCase(usersRepository);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase() {
        return new GetUserByIdUseCase(usersRepository, mapper);
    }

    @Bean
    public GetUsersUseCase getUsersUseCase() {
        return new GetUsersUseCase(usersRepository, mapper);
    }

    @Bean
    public UpdateUserRolesUseCase updateUserRolesUseCase() {
        return new UpdateUserRolesUseCase(usersRepository);
    }

    @Bean
    public ChangeUserRolesUseCase changeUserRolesUseCase() {
        return new ChangeUserRolesUseCase(usersRepository);
    }

    @Bean
    public ChangeUserPasswordUseCase changeUserPasswordUseCase(PasswordEncoder passwordEncoder) {
        return new ChangeUserPasswordUseCase(usersRepository, passwordEncoder);
    }
}
