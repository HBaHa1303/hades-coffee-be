package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Order;

import java.util.Optional;

public interface OrdersRepository {
    Optional<Order> findById(Long id);
    Order save(Order order);
}
