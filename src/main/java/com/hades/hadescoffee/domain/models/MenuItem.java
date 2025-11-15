package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import com.hades.hadescoffee.domain.valueObjects.Money;
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
    Money price;
    final String categoryCode;

    public MenuItem(String code, String name, String description, String imageUrl,
                    MenuItemStatus status, Money price, String categoryCode) {
        if (StringUtils.isBlank(code)) {
            throw new DomainValidationException("common.invalid_input", "code");
        }
        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }

        if (StringUtils.isBlank(description)) {
            throw new DomainValidationException("common.invalid_input", "description");
        }

        if (StringUtils.isBlank(imageUrl)) {
            throw new DomainValidationException("common.invalid_input", "image url");
        }

        if (Objects.isNull(status)) {
            throw new DomainValidationException("common.invalid_input", "status");
        }

        if (StringUtils.isBlank(categoryCode)) {
            throw new DomainValidationException("common.invalid_input", "category code");
        }

        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.price = price;
        this.categoryCode = categoryCode;
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

    public void changeName (String newName) {
        if (StringUtils.isBlank(newName)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }
        this.name = newName;
    }

    public void changeDescription (String newDescription) {
        if (StringUtils.isBlank(newDescription)) {
            throw new DomainValidationException("common.invalid_input", "description");
        }
        this.description = newDescription;
    }
    public void changeImageUrl (String newImageUrl) {
        if (StringUtils.isBlank(newImageUrl)) {
            throw new DomainValidationException("common.invalid_input", "image url");
        }
        this.imageUrl = newImageUrl;
    }

    public void changePrice(Money newPrice) {
        if (newPrice == null || newPrice.isZero()) {
            throw new DomainValidationException("common.invalid_input", "price");
        }
        this.price = newPrice;
    }

}
