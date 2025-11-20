package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetRolesUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateRoleController {
    private final GetRolesUseCase getRolesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public  BaseResponse<List<RoleResponse>> getRoles (Locale locale) {
        List<RoleResponse> useCaseResponse = getRolesUseCase.execute();

        return BaseResponse.<List<RoleResponse>>builder()
                .message(messageSource.getMessage("role.get_success", null, locale))
                .data(useCaseResponse)
                .build();
    }
}