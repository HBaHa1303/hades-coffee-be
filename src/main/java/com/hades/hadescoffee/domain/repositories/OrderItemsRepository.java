package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;

import java.util.Optional;

public interface OrderItemsRepository {
    Optional<OrderItem> findById(Long id);
    void save(OrderItem orderItem);
}
