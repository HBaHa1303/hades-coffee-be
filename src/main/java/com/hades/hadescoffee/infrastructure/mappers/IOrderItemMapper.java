package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IOrderItemMapper {
    default BigDecimal mapMoney (Money value) {
        return value.getAmount();
    }
    default Money mapMoney (BigDecimal value) {
        return new Money(value);
    }

    @Mapping(target = "price", expression = "java(menuItem.getPrice())")
    OrderItemEntity modelToEntity(OrderItem orderItem, MenuItemEntity menuItem, OrderEntity order);

    OrderItem entityToModel(OrderItemEntity orderItem);
}
