package com.hades.hadescoffee.application.exceptions;

public class APermissionException extends ApplicationException {
    public APermissionException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
