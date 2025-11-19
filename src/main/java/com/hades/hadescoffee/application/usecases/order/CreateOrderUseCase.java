package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;

    public void execute(CreateOrderRequest request) {
//        repository.
    }
}
