package com.hades.hadescoffee.domain.exceptions;

public class DInvariantViolationException extends DomainException{
    public DInvariantViolationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
