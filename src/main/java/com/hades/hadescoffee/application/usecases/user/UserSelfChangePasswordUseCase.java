package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.exceptions.AValidationException;
import com.hades.hadescoffee.application.exceptions.ApplicationException;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.domain.vo.Password;
import com.hades.hadescoffee.presentation.dto.requests.user.UserSelfChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSelfChangePasswordUseCase {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    public void execute(UserSelfChangePasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AValidationException("user.confirm_password_not_match");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails principalDetails) {
            String userId = principalDetails.getUsername();
            User user = usersRepository.findById(Long.parseLong(userId))
                    .orElseThrow(() -> new ANotFoundException("user.not_found", userId));
            String currentPassword = request.getCurrentPassword();
            boolean matches = passwordEncoder.matches(currentPassword, user.getPassword().getHashed());
            if (!matches) {
                throw new AValidationException("user.current_password_not_match");
            }
            user.changePassword(new Password(passwordEncoder.encode(request.getNewPassword())));
            usersRepository.save(user);
        } else {
            throw new ApplicationException("security.error.insufficient_authentication");
        }
    }
}
