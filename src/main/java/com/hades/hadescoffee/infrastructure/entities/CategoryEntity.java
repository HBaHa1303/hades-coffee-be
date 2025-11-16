package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.CategoryStatusEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity extends BaseEntity {
    @Id
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CategoryStatusEntity status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<MenuItemEntity> menuItems = List.of();
}
