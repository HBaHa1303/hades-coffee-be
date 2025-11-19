package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.infrastructure.entities.OrderItemEntity;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AOrderItemMapper {
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "price", expression = "java(Money.of(orderItemEntity.getPrice()))")
    @Mapping(target = "quantity", source = "request.quantity")
    OrderItem requestToDomain(CreateOrderItemRequest request, OrderItemEntity orderItemEntity);
}
