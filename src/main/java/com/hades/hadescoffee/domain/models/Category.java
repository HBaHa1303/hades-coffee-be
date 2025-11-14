package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    final String code;
    String name;
    CategoryStatus status;

    public Category (String code, String name, CategoryStatus status){
        if (StringUtils.isBlank(code)) {
            throw new DomainValidationException("common.invalid_input", "code");
        }

        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }

        this.code = code;
        this.name = name;
        this.status = status;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainValidationException("common.invalid_input", "name");
        }
        this.name = newName;
    }

    public void activate() {
        this.status = CategoryStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = CategoryStatus.INACTIVE;
    }

    public boolean isActive() {
        return this.status == CategoryStatus.ACTIVE;
    }
}
