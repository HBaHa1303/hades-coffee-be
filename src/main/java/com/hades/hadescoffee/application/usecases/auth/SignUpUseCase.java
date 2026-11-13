package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.AConflictException;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.application.services.OtpService;
import com.hades.hadescoffee.application.services.SequenceGeneratorService;
import com.hades.hadescoffee.application.services.SignupCacheService;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.presentation.dto.requests.auth.ConfirmSignUpRequest;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpUseCase {
    private final UsersRepository usersRepository;
    private final AUserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final OtpService otpService;
    private final SignupCacheService signupCacheService;
    private final SequenceGeneratorService sequenceGenerator;

    public void execute (ConfirmSignUpRequest request) {
        String otp = otpService.getOtp(request.getEmail());
        if (otp == null) {
            throw new ANotFoundException("auth.otp_not_found");
        }
        if (!request.getOtp().equals(otp)) {
            throw new AConflictException("auth.otp_wrong");
        }
        otpService.deleteOtp(request.getEmail());

        SignUpRequest signUpRequest = signupCacheService.getSignupData(request.getEmail());
        rolesRepository.findByCode("USER")
                .orElseThrow(() -> new ANotFoundException("role.not_found", "user"));
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Long id = sequenceGenerator.generateSequence(UserEntity.SEQUENCE_NAME);
        User user = mapper.signUpRequestToDomain(signUpRequest, hashedPassword, Set.of("USER"), id);

        usersRepository.save(user);
    }

}
