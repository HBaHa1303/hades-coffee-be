package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.exceptions.AValidationException;
import com.hades.hadescoffee.application.services.EmailService;
import com.hades.hadescoffee.application.services.OtpService;
import com.hades.hadescoffee.application.services.SignupCacheService;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestSignUpUseCase {
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final OtpService otpService;
    private final SignupCacheService signupCacheService;

    public void execute(SignUpRequest request) {
        validatePassword(request.getPassword());

        usersRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new AConflictException("user.email_exists", user.getEmail());
                });
        String otp = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1_000_000));
        otpService.saveOtp(request.getEmail(), otp, 60);
        signupCacheService.saveSignupData(request);
        emailService.sendEmail(request.getEmail(), "Otp", "OTP cua ban la: " + otp);
    }


    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new AValidationException("user.password_too_short", 8);
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new AValidationException("user.password_missing_uppercase");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new AValidationException("user.password_missing_lowercase");
        }

        if (!password.matches(".*\\d.*")) {
            throw new AValidationException("user.password_missing_digit");
        }

        if (!password.matches(".*[@#$%^&+=!].*")) {
            throw new AValidationException("user.password_missing_special");
        }
    }
}
