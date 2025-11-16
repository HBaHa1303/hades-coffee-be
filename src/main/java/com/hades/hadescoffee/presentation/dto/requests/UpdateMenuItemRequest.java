package com.hades.hadescoffee.presentation.dto.requests;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.valueObjects.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMenuItemRequest {
    String name;
    String description;
    String imageUrl;
    Money price;
}
