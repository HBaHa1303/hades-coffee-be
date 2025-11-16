package com.hades.hadescoffee.application.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final String messageKey;
    private final Object[] messageArgs;

    public ApplicationException(String messageKey, Object... messageArgs) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageArgs = messageArgs;
    }
}
