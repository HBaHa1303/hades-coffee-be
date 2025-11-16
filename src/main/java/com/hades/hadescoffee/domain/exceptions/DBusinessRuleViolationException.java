package com.hades.hadescoffee.domain.exceptions;

public class DBusinessRuleViolationException extends DomainException {
    public DBusinessRuleViolationException(String messageKey, Object... messageArgs) {
        super(messageKey, messageArgs);
    }
}
