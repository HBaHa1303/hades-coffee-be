package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.vo.Money;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemResponse {
    Long id;
    String name;
    String description;
    String imageUrl;
    MenuItemStatus status;
    BigDecimal price;
    Long categoryId;
}
