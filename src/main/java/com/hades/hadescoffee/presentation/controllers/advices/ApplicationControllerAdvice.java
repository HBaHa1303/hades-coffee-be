package com.hades.hadescoffee.presentation.controllers.advices;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(2)
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationControllerAdvice {
    private final MessageSource messageSource;


}
