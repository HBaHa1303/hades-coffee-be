package com.hades.hadescoffee.presentation.dto.requests.order;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeOrderStatusRequest {
    OrderStatus status;
}
