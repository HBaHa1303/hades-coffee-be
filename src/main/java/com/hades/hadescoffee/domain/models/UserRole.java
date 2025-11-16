package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.RoleStatus;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole {
    final Long id;
    final String roleCode;
    RoleStatus status;

    public UserRole(Long id, String roleCode, RoleStatus status) {
        if (Objects.isNull(roleCode)) {
            throw new DValidationException("common.invalid_input", "role code");
        }
        if (Objects.isNull(status)) {
            throw new DValidationException("common.invalid_input", "role status");
        }

        this.id = id;
        this.roleCode = roleCode;
        this.status = status;
    }

    public void activate() {
        if (status == RoleStatus.ACTIVE)
            throw new DomainException("user.role_already_active", roleCode);
        this.status = RoleStatus.ACTIVE;
    }

    public void revoke() {
        if (status == RoleStatus.INACTIVE)
            throw new DomainException("user.role_already_inactive", roleCode);
        this.status = RoleStatus.INACTIVE;
    }
}
