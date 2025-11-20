package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderItemEntity;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {IOrderItemMapper.class},
        injectionStrategy = InjectionStrategy.FIELD)
public interface IOrderMapper {
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "table", source = "table")
    @Mapping(target = "createdBy", source = "order.createdBy")
    @Mapping(target = "createdTime", source = "order.createdTime")
    @Mapping(target = "lastModifiedBy", source = "order.lastModifiedBy")
    @Mapping(target = "lastModifiedTime", source = "order.lastModifiedTime")
    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "status", source = "order.status")
    OrderEntity modelToEntity(Order order, TableEntity table, List<OrderItemEntity> orderItems);
    Order entityToModel(OrderEntity entity);
}
