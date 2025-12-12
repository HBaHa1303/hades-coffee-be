package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document("orders")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";
    @Id
    Long id;

    Long tableId;
    Long userId;

    OrderStatus status;

    BigDecimal amount;

    List<OrderItemEntity> orderItems = new ArrayList<>();
}
