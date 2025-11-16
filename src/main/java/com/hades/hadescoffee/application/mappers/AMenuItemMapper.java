package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AMenuItemMapper {
    MenuItem createRequestToDomain(CreateMenuItemRequest createMenuItemRequest);
    MenuItemResponse domainToResponse(MenuItem menuItem);
}
