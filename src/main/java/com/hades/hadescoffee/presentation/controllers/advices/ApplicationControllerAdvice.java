package com.hades.hadescoffee.presentation.controllers.advices;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.exceptions.AValidationException;
import com.hades.hadescoffee.application.exceptions.ApplicationException;
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
public class ApplicationControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(value = AConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseResponse<Object> handleAConflictException(AConflictException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }

    @ExceptionHandler(value = ANotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<Object> handleANotFoundException(ANotFoundException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }

    @ExceptionHandler(value = AValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleAValidationException(AValidationException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleApplicationException(ApplicationException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage(ex.getMessageKey(), ex.getMessageArgs(), locale))
                .build();
    }
}
