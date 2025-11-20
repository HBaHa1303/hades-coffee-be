package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.menu.GetMenuItemsByCategoryUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.MenuByCategoryResponse;
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
@RequestMapping(value = "public/api/v1/menu-items",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicMenuItemController {
    private final GetMenuItemsByCategoryUseCase getMenuItemsByCategoryUseCase;
    private final MessageSource messageSource;

    @GetMapping
    BaseResponse<List<MenuByCategoryResponse>> getAllMenuItems(Locale locale) {
        List<MenuByCategoryResponse> response = getMenuItemsByCategoryUseCase.execute();
        return BaseResponse.<List<MenuByCategoryResponse>>builder()
                .message(messageSource.getMessage("menu.get_success", null, locale))
                .data(response)
                .build();
    }
}
