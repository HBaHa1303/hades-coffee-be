package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.presentation.dto.requests.ChangeMenuItemStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemMapper {
    MenuItem createReqestToDomain(CreateMenuItemRequest createMenuItemRequest);
    MenuItem changeStatusRequestToDomain(ChangeMenuItemStatusRequest request);

    MenuItemResponse domainToResponse(MenuItem menuItem);
}
