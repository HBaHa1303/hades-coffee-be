package com.hades.hadescoffee.infrastructure.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity {

    Long menuItemId;
    BigDecimal price;
    Integer quantity;
}
