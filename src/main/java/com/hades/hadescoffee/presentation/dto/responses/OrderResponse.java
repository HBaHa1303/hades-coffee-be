package com.hades.hadescoffee.presentation.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    Long id;
    OrderStatus status;
    Long tableId;
    String fullName;
    BigDecimal totalAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdTime;
    List<OrderItemResponse> orderItems;
}
