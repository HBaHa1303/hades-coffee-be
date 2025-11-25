package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.services.JwtService;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignInRequest;
import com.hades.hadescoffee.presentation.dto.responses.SignInResponse;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignInUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    public SignInResponse execute(SignInRequest request) {
        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ANotFoundException("user.email.not_found", request.getEmail()));
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getId(),
                        request.getPassword()
                )
        );

        String accessToken = jwtService.generateAccessToken(auth.getName());
        String refreshToken = Strings.EMPTY;

        return new SignInResponse(
                user.getEmail().getValue(), accessToken, refreshToken,
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );
    }
}
