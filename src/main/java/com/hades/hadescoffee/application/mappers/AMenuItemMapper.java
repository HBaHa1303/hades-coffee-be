package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.presentation.dto.requests.menuitem.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.responses.MenuByCategoryResponse;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AMenuItemMapper {
    default Money mapMoney (BigDecimal money) {
        return new Money(money);
    }
    default BigDecimal mapMoney (Money money) {
        return money.getAmount();
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    MenuItem createRequestToDomain(CreateMenuItemRequest createMenuItemRequest, Long id, MenuItemStatus status);
    MenuItemResponse domainToResponse(MenuItem menuItem);

    @Mapping(target = "categoryName", source = "cat.name")
    @Mapping(target = "menuItems", source = "items")
    MenuByCategoryResponse mapMenuByCategory(Category cat, List<MenuItem> items);
}
