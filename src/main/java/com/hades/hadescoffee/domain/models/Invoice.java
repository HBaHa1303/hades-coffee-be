package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.InvoiceStatus;
import com.hades.hadescoffee.domain.enums.PaymentMethod;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import com.hades.hadescoffee.domain.valueObjects.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {
    final Long id;
    final Money amount;
    InvoiceStatus status;
    final PaymentMethod paymentMethod;
    final Order order;

    public Invoice(Long id, Money amount, InvoiceStatus status, PaymentMethod paymentMethod, Order order) {
        if (Objects.isNull(amount) || amount.isLessOrEqual(Money.ZERO)) {
            throw new DomainValidationException("common.invalid_input", "amount");
        }

        this.id = id;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public void pay () {
        if (status == InvoiceStatus.PAID) {
            throw new DomainException("invoice.already_paid", id);
        }

        status = InvoiceStatus.PAID;
    }
}
