package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.valueObjects.Money;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import org.mapstruct.Mapper;
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

    MenuItem entityToModel(MenuItemEntity entity);
    MenuItemEntity modelToEntity(MenuItem model);
}
