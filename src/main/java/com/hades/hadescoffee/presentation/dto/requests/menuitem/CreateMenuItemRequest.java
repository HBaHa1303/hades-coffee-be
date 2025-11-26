package com.hades.hadescoffee.presentation.dto.requests.menuitem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMenuItemRequest {
    String name;
    String description;
    String imageUrl;
    BigDecimal price;
    Long categoryId;
}
