package com.hades.hadescoffee.domain.vo;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Password {
    private final String hashed;

    public Password(String hashed) {
        if (StringUtils.isBlank(hashed)) {
            throw new DValidationException("common.invalid_input", "password");
        }

        if (hashed.length() < 8) {
            throw new DValidationException("user.password_too_short", 8);
        }

        if (!hashed.matches(".*[A-Z].*")) {
            throw new DValidationException("user.password_missing_uppercase");
        }

        if (!hashed.matches(".*[a-z].*")) {
            throw new DValidationException("user.password_missing_lowercase");
        }

        if (!hashed.matches(".*\\d.*")) {
            throw new DValidationException("user.password_missing_digit");
        }

        if (!hashed.matches(".*[@#$%^&+=!].*")) {
            throw new DValidationException("user.password_missing_special");
        }

        this.hashed = hashed;
    }
}
