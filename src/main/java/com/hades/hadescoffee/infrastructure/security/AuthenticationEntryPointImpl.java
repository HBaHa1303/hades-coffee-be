package com.hades.hadescoffee.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        Locale locale = LocaleContextHolder.getLocale();

        String messageKey = "security.error.authentication_service";

        if (authException instanceof BadCredentialsException) {
            messageKey = "security.error.bad_credentials";
        } else if (authException instanceof DisabledException) {
            messageKey = "security.error.disabled";
        } else if (authException instanceof LockedException) {
            messageKey = "security.error.locked";
        } else if (authException instanceof AccountExpiredException) {
            messageKey = "security.error.account_expired";
        } else if (authException instanceof CredentialsExpiredException) {
            messageKey = "security.error.credentials_expired";
        } else if (authException instanceof InsufficientAuthenticationException) {
            messageKey = "security.error.insufficient_authentication";
        }

        BaseResponse<Object> jsonResponse = BaseResponse.builder()
                .message(messageSource.getMessage(messageKey, null, locale))
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(jsonResponse));
    }

}
