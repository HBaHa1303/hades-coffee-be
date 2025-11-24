package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.services.EmailService;
import com.hades.hadescoffee.application.services.OtpService;
import com.hades.hadescoffee.application.services.SignupCacheService;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.auth.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class ResetPasswordUseCase {
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final OtpService otpService;
    private final SignupCacheService signupCacheService;

    public void execute(ResetPasswordRequest request) {
        usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ANotFoundException("user.not_found", request.getEmail()));
        String otp = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1_000_000));
        otpService.saveOtp(request.getEmail(), otp, 60);
        emailService.sendEmail(request.getEmail(), "Otp", "OTP cua ban la: " + otp);
    }
}
