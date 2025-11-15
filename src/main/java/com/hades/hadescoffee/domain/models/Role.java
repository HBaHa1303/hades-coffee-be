package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    String code;
    String name;

    public Role(String code, String name) {
        if (StringUtils.isBlank(code)) {
            throw new DomainValidationException("common.invalid_input", "code");
        }

        if (StringUtils.isBlank(name)) {
            throw new DomainValidationException("common.invalid_input", "name");
        }

        this.code = code;
        this.name = name;
    }
}
