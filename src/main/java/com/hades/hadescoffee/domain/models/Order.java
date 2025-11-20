package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseDomain {
    final Long id;
    OrderStatus status;
    final Long tableId;
    final List<OrderItem> orderItems;

    public Order(Long id, OrderStatus status, Long tableId, List<OrderItem> orderItems) {
        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "status");
        }

        this.id = id;
        this.status = status;
        this.tableId = tableId;
        this.orderItems = Objects.isNull(orderItems) ? new ArrayList<>() : orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) {
            throw new DValidationException("common.invalid_input", "orderItem");
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
            case PENDING -> OrderStatus.CONFIRMED;
            case CONFIRMED -> { alreadyInState("confirm"); yield status; }
            case CANCELLED, IN_PROGRESS, READY, SERVED -> { invalidTransition("confirm"); yield status; }
        };
    }

    public void start() {
        status = switch (status) {
            case CONFIRMED -> OrderStatus.IN_PROGRESS;
            case IN_PROGRESS -> { alreadyInState("start"); yield status; }
            case PENDING, CANCELLED, READY, SERVED -> { invalidTransition("start"); yield status; }
        };
    }

    public void pending() {
        status = switch (status) {
            case PENDING -> { alreadyInState("pending"); yield status; }
            case CONFIRMED, IN_PROGRESS, CANCELLED, READY, SERVED -> { invalidTransition("start"); yield status; }
        };
    }

    public void ready() {
        status = switch (status) {
            case IN_PROGRESS -> OrderStatus.READY;
            case READY -> { alreadyInState("ready"); yield status; }
            case PENDING, CONFIRMED, CANCELLED, SERVED -> { invalidTransition("ready"); yield status; }
        };
    }

    public void serve() {
        status = switch (status) {
            case READY -> OrderStatus.SERVED;
            case SERVED -> { alreadyInState("serve"); yield status; }
            case PENDING, CONFIRMED, IN_PROGRESS, CANCELLED -> { invalidTransition("serve"); yield status; }
        };
    }

    public void cancel() {
        status = switch (status) {
            case PENDING, CONFIRMED -> OrderStatus.CANCELLED;
            case CANCELLED -> { alreadyInState("cancel"); yield status; }
            case IN_PROGRESS, READY, SERVED -> { invalidTransition("cancel"); yield status; }
        };
    }
}
