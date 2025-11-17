package com.hades.hadescoffee.application.usecases.auth;

import com.hades.hadescoffee.application.services.JwtService;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignInRequest;
import com.hades.hadescoffee.presentation.dto.responses.SignInResponse;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignInUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public SignInResponse execute(SignInRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String accessToken = jwtService.generateAccessToken(auth.getName());
        String refreshToken = Strings.EMPTY;

        return new SignInResponse(
                auth.getName(), accessToken, refreshToken,
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );
    }
}
