package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.exceptions.DBusinessRuleViolationException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.vo.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem extends BaseDomain {
    final Long id;
    String name;
    String description;
    String imageUrl;
    MenuItemStatus status;
    Money price;
    final Long categoryId;

    public MenuItem(Long id, String name, String description, String imageUrl,
                    MenuItemStatus status, Money price, Long categoryId) {
        if (StringUtils.isBlank(name)) {
            throw new DValidationException("common.invalid_input", "name");
        }

        if (StringUtils.isBlank(description)) {
            throw new DValidationException("common.invalid_input", "description");
        }

        if (StringUtils.isBlank(imageUrl)) {
            throw new DValidationException("common.invalid_input", "image url");
        }

        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "status");
        }

        if (Objects.isNull(categoryId)) {
            throw new DValidationException("common.invalid_input", "category id");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.price = price;
        this.categoryId = categoryId;
    }

    public void changeStatus(MenuItemStatus newStatus) {
        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "status");
        }
        if (status == MenuItemStatus.DISCONTINUED && newStatus == MenuItemStatus.AVAILABLE) {
            throw new DBusinessRuleViolationException("menu.invalid_transition", id, status, "available");
        }

        this.status = newStatus;
    }

    public void changeName (String newName) {
        if (StringUtils.isBlank(newName)) {
            throw new DValidationException("common.invalid_input", "name");
        }
        this.name = newName;
    }

    public void changeDescription (String newDescription) {
        if (StringUtils.isBlank(newDescription)) {
            throw new DValidationException("common.invalid_input", "description");
        }
        this.description = newDescription;
    }
    public void changeImageUrl (String newImageUrl) {
        if (StringUtils.isBlank(newImageUrl)) {
            throw new DValidationException("common.invalid_input", "image url");
        }
        this.imageUrl = newImageUrl;
    }

    public void changePrice(Money newPrice) {
        if (newPrice == null || newPrice.isLessOrEqual(Money.of(BigDecimal.ZERO))) {
            throw new DValidationException("common.invalid_input", "price");
        }
        this.price = newPrice;
    }

}
