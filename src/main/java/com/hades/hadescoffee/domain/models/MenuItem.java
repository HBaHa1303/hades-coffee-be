package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    final String code;
    String name;
    String description;
    String imageUrl;
    MenuItemStatus status;
    Long price;
    final Category category;

    public MenuItem(String code, String name, String description, String imageUrl,
                    MenuItemStatus status, Long price, Category category) {
        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }

        if (StringUtils.isBlank(description)) {
            throw new DomainValidationException("common.invalid_input", "description");
        }

        if (StringUtils.isBlank(imageUrl)) {
            throw new DomainValidationException("common.invalid_input", "image url");
        }

        if (Objects.isNull(price) || price <= 0) {
            throw new DomainValidationException("common.invalid_input", "price");
        }

        if (category == null) {
            throw new DomainValidationException("common.invalid_input", "category");
        }

        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.price = price;
        this.category = category;
    }

    public void activate() {
        this.status = MenuItemStatus.AVAILABLE;
    }

    public void deactivate() {
        this.status = MenuItemStatus.OUT_OF_STOCK;
    }

    public boolean isActive() {
        return status == MenuItemStatus.AVAILABLE;
    }
}
