package com.hades.hadescoffee.infrastructure.configs;


import com.hades.hadescoffee.application.mappers.AInvoiceMapper;
import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.application.usecases.order.ChangeOrderStatusUseCase;
import com.hades.hadescoffee.application.usecases.order.CreateOrderUseCase;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderBeanConfig {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;

    @Bean
    public CreateOrderUseCase createOrderUseCase(MenuItemsRepository menuItemsRepository,
                                                 InvoicesRepository invoicesRepository,
                                                 AInvoiceMapper invoiceMapper) {
        return new CreateOrderUseCase(repository, mapper, menuItemsRepository, invoicesRepository, invoiceMapper);
    }

    @Bean
    public ChangeOrderStatusUseCase  changeOrderStatusUseCase() {
        return new ChangeOrderStatusUseCase(repository, mapper);
    }
}
