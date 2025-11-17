package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.valueObjects.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    final Long id;
    final Long menuItemId;
    final Money price;
    final Integer quantity;
    final Long orderId;

    public OrderItem(Long id, Long menuItemId, Money price, Integer quantity, Long orderId) {
        if (Objects.isNull(menuItemId)) {
            throw new DValidationException("common.invalid_input", "menu item");
        }
        if (Objects.isNull(price) || price.isLessOrEqual(Money.of(BigDecimal.ZERO))) {
            throw new DValidationException("common.invalid_input", "price");
        }

        if (Objects.isNull(quantity) || quantity <= 0) {
            throw new DValidationException("common.invalid_input", "quantity");
        }

        if (Objects.isNull(orderId)) {
            throw new DValidationException("common.invalid_input", "order id");
        }

        this.id = id;
        this.menuItemId = menuItemId;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
