package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.Order;

import java.util.Optional;

public interface OrdersRepository {
    Optional<Order> findById(Long id);
    void save(Order order);
}
