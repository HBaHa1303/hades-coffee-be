package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
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
    OrderEntity modelToEntity(Order order);
    Order entityToModel(OrderEntity entity);
}
