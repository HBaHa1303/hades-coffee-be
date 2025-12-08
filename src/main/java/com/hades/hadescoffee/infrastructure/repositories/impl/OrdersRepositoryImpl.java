package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.infrastructure.entities.*;
import com.hades.hadescoffee.infrastructure.mappers.IOrderItemMapper;
import com.hades.hadescoffee.infrastructure.mappers.IOrderMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.MenuItemsJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.OrdersJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.TablesJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.UsersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
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
    private final UsersJpaRepository usersJpaRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public Order save(Order order) {
        TableEntity tableEntity = tablesJpaRepository.findById(order.getTableId())
                .orElseThrow(() -> new ANotFoundException("table.not_found", order.getTableId()));
        UserEntity userEntity = null;
        if (Objects.nonNull(order.getUserId())) {
            userEntity = usersJpaRepository.findById(order.getUserId())
                    .orElseThrow(() -> new ANotFoundException("user.not_found", order.getUserId()));
        }
        OrderEntity orderEntity = mapper.modelToEntity(order, tableEntity, userEntity, List.of());
        List<OrderItemEntity> orderItems = order.getOrderItems().stream().map(orderItem -> {
            MenuItemEntity menuItemEntity = menuItemJpaRepository.findById(orderItem.getMenuItemId())
                    .orElseThrow(() -> new ANotFoundException("menu.not_found", orderItem.getMenuItemId()));
            return orderItemMapper.modelToEntity(orderItem, menuItemEntity, orderEntity);
        }).toList();

        orderEntity.setOrderItems(orderItems);
        OrderEntity savedOrder = repository.save(orderEntity);

        return mapper.entityToModel(savedOrder);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(mapper::entityToModel).toList();
    }

    @Override
    public List<Order> findByStatuses(List<OrderStatus> statuses) {
        return repository.findByStatusIsIn(statuses).stream().map(mapper::entityToModel).toList();
    }
}
