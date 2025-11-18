package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.auth.SignInUseCase;
import com.hades.hadescoffee.application.usecases.auth.SignUpUseCase;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignInRequest;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/auth",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PublicAuthController {
    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final MessageSource messageSource;

    @PostMapping("sign-in")
    public BaseResponse<SignInResponse> signIn(@RequestBody SignInRequest request, Locale locale) {
        SignInResponse response = signInUseCase.execute(request);
        return BaseResponse.<SignInResponse>builder()
                .message(messageSource.getMessage("auth.sign_in_success", null, locale))
                .data(response)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("sign-up")
    public BaseResponse<Object> signUp(@RequestBody SignUpRequest request, Locale locale) {
        signUpUseCase.execute(request);
        return BaseResponse.<Object>builder()
                .message(messageSource.getMessage("auth.sign_up_success", null, locale))
                .build();
    }
}
