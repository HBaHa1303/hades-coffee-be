package com.hades.hadescoffee.domain.exceptions;

public class DValidationException extends DomainException {
    public DValidationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
