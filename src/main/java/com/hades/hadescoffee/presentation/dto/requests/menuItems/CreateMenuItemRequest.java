package com.hades.hadescoffee.presentation.dto.requests.menuItems;

import com.hades.hadescoffee.domain.valueObjects.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMenuItemRequest {
    String name;
    String description;
    String imageUrl;
    Money price;
    Long categoryId;
}
