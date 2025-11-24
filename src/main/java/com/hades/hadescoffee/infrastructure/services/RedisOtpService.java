package com.hades.hadescoffee.infrastructure.services;

import com.hades.hadescoffee.application.services.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisOtpService implements OtpService {

    private final StringRedisTemplate redis;

    @Override
    public void saveOtp(String key, String otp, long ttlSeconds) {
        redis.opsForValue().set(key, otp, ttlSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String getOtp(String key) {
        return redis.opsForValue().get(key);
    }

    @Override
    public void deleteOtp(String key) {
        redis.delete(key);
    }
}

