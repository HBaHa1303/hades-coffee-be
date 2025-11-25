package com.hades.hadescoffee.presentation.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    Long id;
    Long menuItemId;
    Long price;
    Integer quantity;
}
