package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetOrdersUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;

    public List<OrderResponse> execute(List<OrderStatus> statuses) {
        return repository.findByStatuses(statuses).stream().map(mapper::modelToResponse).toList();
    }
}
