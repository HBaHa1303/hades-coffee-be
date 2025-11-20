package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.responses.MenuByCategoryResponse;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AMenuItemMapper {
    @Mapping(target = "status", source = "status")
    @Mapping(target = "price", expression = "java(new Money(createMenuItemRequest.getPrice()))")
    MenuItem createRequestToDomain(CreateMenuItemRequest createMenuItemRequest, MenuItemStatus status);
    MenuItemResponse domainToResponse(MenuItem menuItem);

    @Mapping(target = "categoryName", source = "cat.name")
    @Mapping(target = "menuItems", source = "items")
    MenuByCategoryResponse mapMenuByCategory(Category cat, List<MenuItem> items);
}
