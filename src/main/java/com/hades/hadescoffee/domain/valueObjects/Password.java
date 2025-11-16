package com.hades.hadescoffee.domain.valueObjects;

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
        this.hashed = hashed;
    }
}
