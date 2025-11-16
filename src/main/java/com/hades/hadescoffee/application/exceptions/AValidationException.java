package com.hades.hadescoffee.application.exceptions;

public class AValidationException extends ApplicationException {
    public AValidationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
