package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.infrastructure.entities.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {IOrderItemMapper.class},
        injectionStrategy = InjectionStrategy.FIELD)
public interface IOrderMapper {
    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }
    default BigDecimal map(Money money) {
        return money == null ? null : money.getAmount();
    }

    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "table", source = "table")
    @Mapping(target = "createdBy", source = "order.createdBy")
    @Mapping(target = "createdTime", source = "order.createdTime")
    @Mapping(target = "lastModifiedBy", source = "order.lastModifiedBy")
    @Mapping(target = "lastModifiedTime", source = "order.lastModifiedTime")
    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "status", source = "order.status")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "amount", expression = "java(map(order.getTotalAmount()))")
    OrderEntity modelToEntity(Order order, TableEntity table, UserEntity user, List<OrderItemEntity> orderItems);

    @Mapping(target = "tableId", source = "table.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "totalAmount", expression = "java(map(entity.getAmount()))")
    Order entityToModel(OrderEntity entity);
}
