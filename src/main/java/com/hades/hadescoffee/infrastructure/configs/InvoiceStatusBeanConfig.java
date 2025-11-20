package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.usecases.GetCategoryStatusesUseCase;
import com.hades.hadescoffee.application.usecases.GetInvoiceStatusesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InvoiceStatusBeanConfig {
    @Bean
    public GetInvoiceStatusesUseCase getInvoiceStatusesUseCase() {
        return new GetInvoiceStatusesUseCase();
    }
}
