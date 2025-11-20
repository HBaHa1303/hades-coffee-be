package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.ARoleMapper;
import com.hades.hadescoffee.application.usecases.GetOrderStatusesUseCase;
import com.hades.hadescoffee.application.usecases.GetRolesUseCase;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderStatusBeanConfig {

    @Bean
    public GetOrderStatusesUseCase getOrderStatusesUseCase() {
        return new GetOrderStatusesUseCase();
    }
}
