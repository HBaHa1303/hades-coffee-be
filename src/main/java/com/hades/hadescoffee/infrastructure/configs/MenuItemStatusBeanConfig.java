package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.usecases.GetMenuItemStatusesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MenuItemStatusBeanConfig {

    @Bean
    public GetMenuItemStatusesUseCase getMenuItemStatusesUseCase() {
        return new GetMenuItemStatusesUseCase();
    }
}

