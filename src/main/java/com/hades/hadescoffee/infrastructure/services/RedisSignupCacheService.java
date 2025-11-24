package com.hades.hadescoffee.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hades.hadescoffee.application.services.SignupCacheService;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisSignupCacheService implements SignupCacheService{

    private final StringRedisTemplate redis;
    private final ObjectMapper objectMapper;

    private static final long TTL_MINUTES = 1;

    @SneakyThrows
    public void saveSignupData(SignUpRequest data) {
        String key = "signup:" + data.getEmail();

        String json = objectMapper.writeValueAsString(data);

        redis.opsForValue().set(key, json, Duration.ofMinutes(TTL_MINUTES));
    }

    @SneakyThrows
    public SignUpRequest getSignupData(String email) {
        String key = "signup:" + email;

        String json = redis.opsForValue().get(key);
        if (json == null) return null;

        return objectMapper.readValue(json, SignUpRequest.class);
    }

    public void deleteSignupData(String email) {
        redis.delete("signup:" + email);
    }
}
