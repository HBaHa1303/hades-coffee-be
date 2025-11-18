package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole {
    final Long id;
    final String roleCode;

    public UserRole(Long id, String roleCode) {
        if (Objects.isNull(roleCode)) {
            throw new DValidationException("common.invalid_input", "role code");
        }

        this.id = id;
        this.roleCode = roleCode;
    }
}
