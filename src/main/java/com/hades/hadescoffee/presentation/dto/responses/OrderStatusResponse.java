package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderStatusResponse {
    OrderStatus status;
}
