package com.hades.hadescoffee.presentation.controllers.advices;

import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order(3)
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleException(Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("system.error", null, locale))
                .build();
    }
}
