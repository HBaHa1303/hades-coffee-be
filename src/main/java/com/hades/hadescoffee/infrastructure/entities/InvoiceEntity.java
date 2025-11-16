package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.InvoiceStatusEntity;
import com.hades.hadescoffee.infrastructure.enums.PaymentMethodEntity;
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

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal amount;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    InvoiceStatusEntity status;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethodEntity paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    OrderEntity order;
}
