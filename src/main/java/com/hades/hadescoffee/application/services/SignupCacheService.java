package com.hades.hadescoffee.application.services;

import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;

public interface SignupCacheService {
    void saveSignupData(SignUpRequest data);
    SignUpRequest getSignupData(String email);
    void deleteSignupData(String email);
}
