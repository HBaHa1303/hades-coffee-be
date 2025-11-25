package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    Long id;
    OrderStatus status;
    Long tableId;
    Long userId;
    List<OrderItemResponse> orderItems;
}
