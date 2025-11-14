package com.hades.hadescoffee.domain.exceptions;

public class DomainValidationException extends DomainException {
    public DomainValidationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
