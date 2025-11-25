package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.presentation.dto.requests.order.ChangeOrderStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeOrderStatusUseCase {
    private final OrdersRepository repository;

    public void execute(Long id, ChangeOrderStatusRequest request) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ANotFoundException("order.not_found", id));
        switch (request.getStatus()) {
            case PENDING -> order.pending();
            case CONFIRMED -> order.confirm();
            case IN_PROGRESS -> order.start();
            case READY -> order.ready();
            case CANCELLED -> order.cancel();
            case SERVED -> order.serve();
        }
        repository.save(order);
    }
}
