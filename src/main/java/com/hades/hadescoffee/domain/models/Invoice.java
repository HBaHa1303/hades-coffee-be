package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.InvoiceStatus;
import com.hades.hadescoffee.domain.enums.PaymentMethod;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.vo.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice extends BaseDomain {
    final Long id;
    final Money amount;
    InvoiceStatus status;
    final PaymentMethod paymentMethod;
    final Long orderId;

    public Invoice(Long id, Money amount, InvoiceStatus status, PaymentMethod paymentMethod, Long orderId) {
        if (Objects.isNull(amount) || amount.isLessOrEqual(Money.of(BigDecimal.ZERO))) {
            throw new DValidationException("common.invalid_input", "amount");
        }
        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "status");
        }
        if (Objects.isNull(paymentMethod)) {
            throw new DValidationException("common.invalid_input", "payment method");
        }
        if (Objects.isNull(orderId)) {
            throw new DValidationException("common.invalid_input", "order id");
        }

        this.id = id;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
    }

    public void pay () {
        if (status == InvoiceStatus.PAID) {
            throw new DomainException("invoice.already_paid", id);
        }

        status = InvoiceStatus.PAID;
    }
}
