package com.hades.hadescoffee.presentation.controllers.advices;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order(2)
@RestControllerAdvice
@RequiredArgsConstructor
public class DomainControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(value = DValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleDValidationException(DValidationException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }

    @ExceptionHandler(value = DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleDomainException(DomainException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }
}
