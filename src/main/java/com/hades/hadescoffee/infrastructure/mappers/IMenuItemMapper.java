package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.valueObjects.Money;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMenuItemMapper {
    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }
    default BigDecimal map(Money money) {
        return money == null ? null : money.getAmount();
    }

    @Mapping(target = "categoryId", source = "category.id")
    MenuItem entityToModel(MenuItemEntity entity);

    @Mapping(target = "id", source = "menuItem.id")
    @Mapping(target = "name", source = "menuItem.name")
    @Mapping(target = "status", source = "menuItem.status")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "createdBy", source = "menuItem.createdBy")
    @Mapping(target = "createdTime", source = "menuItem.createdTime")
    @Mapping(target = "lastModifiedBy", source = "menuItem.lastModifiedBy")
    @Mapping(target = "lastModifiedTime", source = "menuItem.lastModifiedTime")
    MenuItemEntity modelToEntity(MenuItem menuItem, CategoryEntity category);
}
