package com.hades.hadescoffee.presentation.controllers.advices;

import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order(1)
@RestControllerAdvice
@RequiredArgsConstructor
public class SecurityControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleBadCredentials(BadCredentialsException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.bad_credentials", null, locale))
                .build();
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleDisabled(DisabledException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.disabled", null, locale))
                .build();
    }

    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleLocked(LockedException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.locked", null, locale))
                .build();
    }

    @ExceptionHandler(AccountExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleAccountExpired(AccountExpiredException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.account_expired", null, locale))
                .build();
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleCredentialsExpired(CredentialsExpiredException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.credentials_expired", null, locale))
                .build();
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse<Object> handleInsufficientAuth(InsufficientAuthenticationException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.insufficient_authentication", null, locale))
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse<Object> handleAccessDenied(AccessDeniedException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.access_denied", null, locale))
                .build();
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleAuthServiceException(AuthenticationServiceException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.authentication_service", null, locale))
                .build();
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse<Object> handleSessionAuthException(SessionAuthenticationException ex, Locale locale) {
        return BaseResponse.builder()
                .message(messageSource.getMessage("security.error.session_authentication", null, locale))
                .build();
    }
}

