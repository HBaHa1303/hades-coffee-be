package com.hades.hadescoffee.presentation.dto.requests.menuItems;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.presentation.dto.requests.BaseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterMenuItemsRequest extends BaseFilterRequest {
    MenuItemStatus status;
}
