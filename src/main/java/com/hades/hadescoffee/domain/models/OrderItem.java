package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class OrderItem {
    Long id;
    MenuItem menuItem;
    Long price;
    Integer quantity;
    Order order;

    public OrderItem(Long id, MenuItem menuItem, Long price, Integer quantity, Order order) {
        if (Objects.isNull(menuItem)) {
            throw new DomainValidationException("common.invalid_input", "menu item");
        }
        if (Objects.isNull(price) || price <= 0) {
            throw new DomainValidationException("common.invalid_input", "price");
        }

        if (Objects.isNull(quantity) || quantity <= 0) {
            throw new DomainValidationException("common.invalid_input", "quantity");
        }

        if (Objects.isNull(order)) {
            throw new DomainValidationException("common.invalid_input", "order");
        }

        this.id = id;
        this.menuItem = menuItem;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }
}
