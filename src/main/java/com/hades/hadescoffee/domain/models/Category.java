package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.infrastructure.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseDomain {
    final Long id;
    String name;
    CategoryStatus status;

    public Category (Long id, String name, CategoryStatus status){
        if (StringUtils.isBlank(name)) {
            throw new DValidationException("common.invalid_input", "name");
        }
        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "status");
        }

        this.id = id;
        this.name = name;
        this.status = status;
    }

    public void rename(String newName) {
        if (StringUtils.isBlank(newName)) {
            throw new DValidationException("common.invalid_input", "name");
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
