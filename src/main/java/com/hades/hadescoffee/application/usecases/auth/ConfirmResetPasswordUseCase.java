package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.exceptions.AValidationException;
import com.hades.hadescoffee.application.services.EmailService;
import com.hades.hadescoffee.application.services.OtpService;
import com.hades.hadescoffee.application.utils.EmailTemplate;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.domain.vo.Password;
import com.hades.hadescoffee.presentation.dto.requests.auth.ConfirmResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmResetPasswordUseCase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    public void execute(ConfirmResetPasswordRequest request) {
        String otp = otpService.getOtp(request.getEmail());
        if (otp == null) {
            throw new ANotFoundException("auth.otp_not_found");
        }
        if (!request.getOtp().equals(otp)) {
            throw new AConflictException("auth.otp_wrong");
        }

        otpService.deleteOtp(request.getEmail());

        String randomPassword = UUID.randomUUID().toString().substring(0, 8);

        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AValidationException("User not found"));
        user.changePassword(new Password(passwordEncoder.encode(randomPassword)));
        usersRepository.save(user);

        emailService.sendEmail(
                request.getEmail(),
                "Khôi phục mật khẩu thành công",
                EmailTemplate.resetPasswordSuccess(randomPassword)
        );

    }
}

