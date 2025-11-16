package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    final String code;
    final String name;

    public Role(String code, String name) {
        if (StringUtils.isBlank(code)) {
            throw new DValidationException("common.invalid_input", "code");
        }

        if (StringUtils.isBlank(name)) {
            throw new DValidationException("common.invalid_input", "name");
        }

        this.code = code;
        this.name = name;
    }
}
