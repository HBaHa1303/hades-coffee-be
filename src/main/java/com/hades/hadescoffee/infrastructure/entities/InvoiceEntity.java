package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.InvoiceStatus;
import com.hades.hadescoffee.domain.enums.PaymentMethod;
import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("invoices")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceEntity extends BaseEntity {

    @Id
    Long id;

    BigDecimal amount;
    InvoiceStatus status;
    PaymentMethod paymentMethod;

    Long orderId;
}
