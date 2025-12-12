package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AOrderMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Order createOrderToModel(CreateOrderRequest order);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "status", expression = "java(OrderStatus.PENDING)")
    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "userId", expression = "java(Long.parseLong(userId))")
    @Mapping(target = "totalAmount", source = "totalAmount")
    Order createOrderByOrderItemsAndTableIdRequest(List<OrderItem> orderItems, Long tableId, String userId, Money totalAmount, Long id);

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "createdTime", source = "order.createdTime")
    @Mapping(target = "fullName", expression = "java(user.getLastName() + \" \" +user.getFirstName())")
    @Mapping(target = "tableName", source = "table.name")
    @Mapping(target = "status", source = "order.status")
    OrderResponse modelToResponse(Order order, User user, Table table);

    OrderResponse modelToResponse(Order order);

    default LocalDateTime map(Instant value) {
        if (value == null) return null;
        return value.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    default Long mapMoney(Money money) {
        return money.getAmount().longValue();
    }
}
