package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.usecases.GetCategoryStatusesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryStatusBeanConfig {
    @Bean
    public GetCategoryStatusesUseCase getCategoryStatusesUseCase() {
        return new GetCategoryStatusesUseCase();
    }
}
