package com.hades.hadescoffee.presentation.dto.requests.order;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.OrderItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateOrderRequest {
    List<CreateOrderItemRequest> orderItems;
}
