package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetOrderByIdUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;

    public OrderResponse execute(Long id) {
        return repository.findById(id).map(mapper::modelToResponse)
                .orElseThrow(() -> new ANotFoundException("order.not_found", id));
    }
}
