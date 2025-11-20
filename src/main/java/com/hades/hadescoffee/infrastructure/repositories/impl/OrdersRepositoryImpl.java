package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderItemEntity;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import com.hades.hadescoffee.infrastructure.mappers.IOrderItemMapper;
import com.hades.hadescoffee.infrastructure.mappers.IOrderMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.MenuItemsJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.OrdersJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.TablesJpaRepository;
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
    private final TablesJpaRepository tablesJpaRepository;
    private final IOrderItemMapper orderItemMapper;
    private final MenuItemsJpaRepository menuItemJpaRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        TableEntity tableEntity = tablesJpaRepository.findById(order.getTableId())
                .orElseThrow(() -> new ANotFoundException("table.not_found", order.getTableId()));

        OrderEntity orderEntity = mapper.modelToEntity(order, tableEntity, List.of());
        List<OrderItemEntity> orderItems = order.getOrderItems().stream().map(orderItem -> {
            MenuItemEntity menuItemEntity = menuItemJpaRepository.findById(orderItem.getMenuItemId())
                    .orElseThrow(() -> new ANotFoundException("menu.not_found", orderItem.getMenuItemId()));
            return orderItemMapper.modelToEntity(orderItem, menuItemEntity, orderEntity);
        }).toList();

        orderEntity.setOrderItems(orderItems);
        OrderEntity savedOrder = repository.save(orderEntity);

        return mapper.entityToModel(savedOrder);
    }
}
