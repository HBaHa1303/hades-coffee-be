package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.mappers.IOrderMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.MenuItemsJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.OrdersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl implements OrdersRepository {
    private final OrdersJpaRepository repository;
    private final IOrderMapper mapper;
    private final MenuItemsJpaRepository menuItemsJpaRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        List<MenuItemEntity> menuItemEntities = menuItemsJpaRepository.findAllById(order.getOrderItems().stream()
                .map(OrderItem::getMenuItemId).collect(Collectors.toSet()));
        OrderEntity orderEntity = mapper.modelToEntity(order);
        OrderEntity savedOrder = repository.save(orderEntity);

        return mapper.entityToModel(savedOrder);
    }
}
