package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("menu_items")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemEntity extends BaseEntity {

    @Id
    Long id;

    String name;
    String description;
    String imageUrl;
    MenuItemStatus status;
    BigDecimal price;

    Long categoryId;
}
