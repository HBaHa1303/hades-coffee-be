package com.hades.hadescoffee.presentation.dto.requests.menuitem;

import com.hades.hadescoffee.domain.vo.Money;
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
