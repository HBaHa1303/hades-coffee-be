package com.hades.hadescoffee.domain.exceptions;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final String messageKey;
    private final Object[] messageArgs;

    public DomainException(String messageKey, Object... messageArgs) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageArgs = messageArgs;
    }
}
