package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.MenuItemStatusEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "menu_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemEntity extends BaseEntity{
    @Id
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false,  length = 500)
    String description;

    @Column(nullable = false)
    String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MenuItemStatusEntity status;

    @Column(nullable = false)
    Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;
}
