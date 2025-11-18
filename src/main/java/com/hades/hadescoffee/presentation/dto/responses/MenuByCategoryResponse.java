package com.hades.hadescoffee.presentation.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuByCategoryResponse {
    private Long categoryId;
    private String categoryName;
    private List<MenuItemResponse> menuItems;
}
