package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.usecases.GetPaymentMethodsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentMethodBeanConfig {
    @Bean
    public GetPaymentMethodsUseCase getPaymentMethodsUseCase() {
        return new GetPaymentMethodsUseCase();
    }
}
