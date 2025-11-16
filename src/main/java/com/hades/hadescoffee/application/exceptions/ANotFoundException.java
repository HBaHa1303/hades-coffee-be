package com.hades.hadescoffee.application.exceptions;

public class ANotFoundException extends ApplicationException {
    public ANotFoundException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
