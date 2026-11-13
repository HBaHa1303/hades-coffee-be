package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdersMongoRepository extends MongoRepository<OrderEntity, Long> {

    List<OrderEntity> findByStatusIn(List<OrderStatus> statuses, Pageable pageable);

    List<OrderEntity> findAllByUserId(Long userId, Pageable pageable);
}