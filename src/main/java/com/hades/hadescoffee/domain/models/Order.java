package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import com.hades.hadescoffee.infrastructure.enums.OrderStatusEntity;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Order {
    final Long id;
    OrderStatusEntity status;
    List<OrderItem> orderItems;

    public Order(Long id, OrderStatusEntity status, List<OrderItem> orderItems) {
        if (Objects.isNull(status)) {
            throw new DomainValidationException("common.invalid_input", "status");
        }

        if (Objects.isNull(orderItems)) {
            throw new DomainValidationException("common.invalid_input", "orderItems");
        }

        this.id = id;
        this.status = status;
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) {
            throw new DomainValidationException("common.invalid_input", "orderItem");
        }

        orderItems.add(orderItem);
    }

    private void alreadyInState(String action) {
        throw new DomainException("order.already_in_state", id, status, action);
    }

    private void invalidTransition(String action) {
        throw new DomainException("order.invalid_transition", id, status, action);
    }

    public void confirm() {
        status = switch (status) {
            case PENDING -> OrderStatusEntity.CONFIRMED;
            case CONFIRMED -> { alreadyInState("confirm"); yield status; }
            case CANCELLED, IN_PROGRESS, READY, SERVED -> { invalidTransition("confirm"); yield status; }
        };
    }

    public void start() {
        status = switch (status) {
            case CONFIRMED -> OrderStatusEntity.IN_PROGRESS;
            case IN_PROGRESS -> { alreadyInState("start"); yield status; }
            case PENDING, CANCELLED, READY, SERVED -> { invalidTransition("start"); yield status; }
        };
    }

    public void ready() {
        status = switch (status) {
            case IN_PROGRESS -> OrderStatusEntity.READY;
            case READY -> { alreadyInState("ready"); yield status; }
            case PENDING, CONFIRMED, CANCELLED, SERVED -> { invalidTransition("ready"); yield status; }
        };
    }

    public void serve() {
        status = switch (status) {
            case READY -> OrderStatusEntity.SERVED;
            case SERVED -> { alreadyInState("serve"); yield status; }
            case PENDING, CONFIRMED, IN_PROGRESS, CANCELLED -> { invalidTransition("serve"); yield status; }
        };
    }

    public void cancel() {
        status = switch (status) {
            case PENDING, CONFIRMED -> OrderStatusEntity.CANCELLED;
            case CANCELLED -> { alreadyInState("cancel"); yield status; }
            case IN_PROGRESS, READY, SERVED -> { invalidTransition("cancel"); yield status; }
        };
    }
}
