package com.hades.hadescoffee.domain.exceptions;

public class DNotEnoughResourceException extends DomainException {
    public DNotEnoughResourceException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
