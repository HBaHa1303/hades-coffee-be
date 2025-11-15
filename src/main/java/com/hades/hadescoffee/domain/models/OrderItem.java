package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import com.hades.hadescoffee.domain.valueObjects.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    final Long id;
    final MenuItem menuItem;
    final Money price;
    final Integer quantity;
    final Long orderId;

    public OrderItem(Long id, MenuItem menuItem, Money price, Integer quantity, Long orderId) {
        if (Objects.isNull(menuItem)) {
            throw new DomainValidationException("common.invalid_input", "menu item");
        }
        if (Objects.isNull(price) || price.isLessOrEqual(Money.ZERO)) {
            throw new DomainValidationException("common.invalid_input", "price");
        }

        if (Objects.isNull(quantity) || quantity <= 0) {
            throw new DomainValidationException("common.invalid_input", "quantity");
        }

        if (Objects.isNull(orderId)) {
            throw new DomainValidationException("common.invalid_input", "order id");
        }

        this.id = id;
        this.menuItem = menuItem;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
