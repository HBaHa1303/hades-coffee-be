package com.hades.hadescoffee.infrastructure.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hades.hadescoffee.application.exceptions.ApplicationException;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspectConfig {
    private final ObjectMapper objectMapper;

    @Around(value = "within(com.hades.hadescoffee.application.usecases..* ||" +
            "com.hades.hadescoffee.infrastructure.repositories..*)")
    public Object logUseCasesAndRepositories(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            log.info("{}::{} - Started",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());

            Object proceed = joinPoint.proceed();

            log.info("{}::{} - Ended - {} ms",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start);

            return proceed;
        } catch (DomainException ex) {
            log.error("{}::{} - DomainException - {} ms - key: {} - message: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start,
                    ex.getMessage(),
                    Arrays.toString(ex.getMessageArgs()));
            throw ex;
        } catch (ApplicationException ex) {
            log.error("{}::{} - ApplicationException - {} ms - key: {} - message: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start,
                    ex.getMessage(),
                    Arrays.toString(ex.getMessageArgs()));
            throw ex;
        } catch (Throwable throwable) {
            log.error("{}::{} - Exception - {} ms - message: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start,
                    throwable.getMessage());
            throw throwable;
        }
    }

    @Around(value = "within(com.hades.hadescoffee.presentation.controllers.privates..* || " +
            "com.hades.hadescoffee.presentation.controllers.publics..*)")
    public Object logUseControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            log.debug("{}::{} - Started - Request: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    objectMapper.writeValueAsString(joinPoint.getArgs()));

            Object proceed = joinPoint.proceed();

            log.debug("{}::{} - Ended - {} ms - Response: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start,
                    objectMapper.writeValueAsString(proceed));

            return proceed;
        } catch (Throwable throwable) {
            log.error("{}::{} - Exception - {} ms",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start,
                    throwable);
            throw throwable;
        }
    }

    @Around("within(com.hades.hadescoffee.presentation.controllers.advices..*)")
    public Object logControllerAdvices(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        log.debug("{}::{} - Ended - {} ms - Response: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                System.currentTimeMillis() - start,
                objectMapper.writeValueAsString(proceed));

        return proceed;
    }

}