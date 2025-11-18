package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "menu_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false, length = 500)
    String description;

    @Column(nullable = false)
    String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MenuItemStatus status;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;
}
