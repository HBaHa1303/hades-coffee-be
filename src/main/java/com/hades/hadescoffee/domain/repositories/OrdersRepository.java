package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    Optional<Order> findById(Long id);
    Order save(Order order);
    List<Order> findAll();
    List<Order> findByStatuses(List<OrderStatus> statuses);
    List<Order> findAllByUserId(long userId);
}
