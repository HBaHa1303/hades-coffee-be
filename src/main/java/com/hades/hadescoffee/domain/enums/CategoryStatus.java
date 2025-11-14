package com.hades.hadescoffee.domain.enums;

public enum CategoryStatus {
    ACTIVE,
    INACTIVE;

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean canDelete() {
        return this == INACTIVE;
    }
}
