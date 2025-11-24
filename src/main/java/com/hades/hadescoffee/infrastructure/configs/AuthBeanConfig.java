package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.application.services.JwtService;
import com.hades.hadescoffee.application.usecases.auth.SignInUseCase;
import com.hades.hadescoffee.application.usecases.auth.SignUpUseCase;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfig {
    private final UsersRepository repository;
    private final AUserMapper mapper;
    private final RolesRepository rolesRepository;

    @Bean
    public SignUpUseCase signUpUseCase(PasswordEncoder passwordEncoder) {
        return new SignUpUseCase(repository, mapper, passwordEncoder, rolesRepository);
    }

    @Bean
    public SignInUseCase signInUseCase(AuthenticationManager authenticationManager,
                                       JwtService jwtService) {
        return new SignInUseCase(authenticationManager, jwtService, repository);
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

}
