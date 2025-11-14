package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.InvoiceStatusEnum;
import com.hades.hadescoffee.infrastructure.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "invoices")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long amount;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    InvoiceStatusEnum status;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethodEnum paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    OrderEntity order;
}
