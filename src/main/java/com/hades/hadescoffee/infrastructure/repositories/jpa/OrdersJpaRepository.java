package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersJpaRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByStatusIsIn(List<OrderStatus> statuses);
}
