package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.ARoleMapper;
import com.hades.hadescoffee.application.usecases.GetRolesUseCase;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoleBeanConfig {
    private final RolesRepository repository;
    private final ARoleMapper mapper;

    @Bean
    public GetRolesUseCase getRolesUseCase() {
        return new GetRolesUseCase(repository, mapper);
    }
}
