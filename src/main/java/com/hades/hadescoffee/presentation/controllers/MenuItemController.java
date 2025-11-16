package com.hades.hadescoffee.presentation.controllers;

import com.hades.hadescoffee.application.usecases.menu.CreateMenuItemUseCase;
import com.hades.hadescoffee.presentation.dto.requests.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/menu-items")
public class MenuItemController {
    private final MessageSource messageSource;
    private final CreateMenuItemUseCase createUseCase;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<Object> createMenuItem(
            @RequestBody CreateMenuItemRequest request,
            Locale locale) {

        createUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("menuItem.created_success", null, locale))
                .build();
    }


}
