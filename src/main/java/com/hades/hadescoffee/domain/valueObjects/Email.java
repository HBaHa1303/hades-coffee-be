package com.hades.hadescoffee.domain.valueObjects;

import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import groovy.transform.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Email {
    String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public Email(String value) {
        if (StringUtils.isBlank(value)) {
            throw new DomainValidationException("common.invalid_input", "email");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new DomainValidationException("common.invalid_input_format", "email");
        }
        this.value = value.toLowerCase();
    }
}
