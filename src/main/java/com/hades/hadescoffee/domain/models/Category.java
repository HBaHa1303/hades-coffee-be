package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    final String code;
    String name;
    CategoryStatus status;
    List<MenuItem> menuItems;

    public Category (String code, String name, CategoryStatus status, List<MenuItem> menuItems){
        if (StringUtils.isBlank(code)) {
            throw new DomainValidationException("common.invalid_input", "code");
        }

        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }

        this.code = code;
        this.name = name;
        this.status = status;
        this.menuItems = Objects.isNull(menuItems) ? List.of() : menuItems;
    }

    public void rename(String newName) {
        if (StringUtils.isBlank(newName)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }
        this.name = newName;
    }

    public void activate() {
        this.status = CategoryStatus.ACTIVE;
        menuItems.forEach(MenuItem::activate);
    }

    public void deactivate() {
        this.status = CategoryStatus.INACTIVE;
        menuItems.forEach(MenuItem::deactivate);
    }

    public boolean isActive() {
        return this.status == CategoryStatus.ACTIVE;
    }

    public void addMenuItem(MenuItem item) {
        if (Objects.isNull(item)) {
            throw new DomainValidationException("common.invalid_input", "menuItem");
        }
        menuItems.add(item);
    }
}
