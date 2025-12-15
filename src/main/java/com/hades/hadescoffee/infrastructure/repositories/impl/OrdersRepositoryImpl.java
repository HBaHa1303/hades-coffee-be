package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.infrastructure.entities.*;
import com.hades.hadescoffee.infrastructure.mappers.IOrderItemMapper;
import com.hades.hadescoffee.infrastructure.mappers.IOrderMapper;
import com.hades.hadescoffee.infrastructure.repositories.mongo.MenuItemsMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.OrdersMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.TablesMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.UsersMongoRepository;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl implements OrdersRepository {
    private final OrdersMongoRepository repository;
    private final IOrderMapper mapper;
    private final TablesMongoRepository tablesMongoRepository;
    private final IOrderItemMapper orderItemMapper;
    private final MenuItemsMongoRepository menuItemMongoRepository;
    private final UsersMongoRepository usersMongoRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public Order save(Order order) {
        TableEntity tableEntity = tablesMongoRepository.findById(order.getTableId())
                .orElseThrow(() -> new ANotFoundException("table.not_found", order.getTableId()));
        UserEntity userEntity = null;
        if (Objects.nonNull(order.getUserId())) {
            userEntity = usersMongoRepository.findById(order.getUserId())
                    .orElseThrow(() -> new ANotFoundException("user.not_found", order.getUserId()));
        }
        OrderEntity orderEntity = mapper.modelToEntity(order, tableEntity, userEntity, List.of());
        List<OrderItemEntity> orderItems = order.getOrderItems().stream().map(orderItem -> {
            MenuItemEntity menuItemEntity = menuItemMongoRepository.findById(orderItem.getMenuItemId())
                    .orElseThrow(() -> new ANotFoundException("menu.not_found", orderItem.getMenuItemId()));
            return orderItemMapper.modelToEntity(orderItem, menuItemEntity, orderEntity);
        }).toList();

        orderEntity.setOrderItems(orderItems);
        OrderEntity savedOrder = repository.save(orderEntity);

        return mapper.entityToModel(savedOrder);
    }

    @Override
    public Order insert(Order order) {
        TableEntity tableEntity = tablesMongoRepository.findById(order.getTableId())
                .orElseThrow(() -> new ANotFoundException("table.not_found", order.getTableId()));
        UserEntity userEntity = null;
        if (Objects.nonNull(order.getUserId())) {
            userEntity = usersMongoRepository.findById(order.getUserId())
                    .orElseThrow(() -> new ANotFoundException("user.not_found", order.getUserId()));
        }
        OrderEntity orderEntity = mapper.modelToEntity(order, tableEntity, userEntity, List.of());
        List<OrderItemEntity> orderItems = order.getOrderItems().stream().map(orderItem -> {
            MenuItemEntity menuItemEntity = menuItemMongoRepository.findById(orderItem.getMenuItemId())
                    .orElseThrow(() -> new ANotFoundException("menu.not_found", orderItem.getMenuItemId()));
            return orderItemMapper.modelToEntity(orderItem, menuItemEntity, orderEntity);
        }).toList();

        orderEntity.setOrderItems(orderItems);
        OrderEntity savedOrder = repository.insert(orderEntity);

        return mapper.entityToModel(savedOrder);
    }

    @Override
    public List<Order> findByStatuses(List<OrderStatus> statuses) {
        Pageable pageable = Pageable.unpaged(Sort.by(Sort.Direction.DESC, "id"));
        return repository.findByStatusIn(statuses, pageable).stream().map(mapper::entityToModel).toList();
    }

    @Override
    public List<Order> findAllByUserId(long userId) {
        Pageable pageable = Pageable.unpaged(Sort.by(Sort.Direction.DESC, "id"));
        return repository.findAllByUserId(userId, pageable).stream().map(mapper::entityToModel).toList();
    }
}
