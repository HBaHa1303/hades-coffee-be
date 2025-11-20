package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetMenuItemStatusesUseCase;
import com.hades.hadescoffee.application.usecases.category.*;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "private/api/v1/menu-item-statuses",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateMenuItemStatusController {

    private final GetMenuItemStatusesUseCase getMenuItemStatusesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<MenuItemStatusResponse>> getStatuses(Locale locale) {
        var data = getMenuItemStatusesUseCase.execute();

        return BaseResponse.<List<MenuItemStatusResponse>>builder()
                .message(messageSource.getMessage("menuItemStatus.get_success", null, locale))
                .data(data)
                .build();
    }
}
