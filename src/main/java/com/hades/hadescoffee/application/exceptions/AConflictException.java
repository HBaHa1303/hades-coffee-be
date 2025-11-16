package com.hades.hadescoffee.application.exceptions;

public class AConflictException extends ApplicationException {
    public AConflictException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
