package com.hades.hadescoffee.application.services;

public interface OtpService {
    void saveOtp(String key, String otp, long ttlSeconds);
    String getOtp(String key);
    void deleteOtp(String key);
}
