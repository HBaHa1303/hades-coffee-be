package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.order.ChangeOrderStatusUseCase;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.ChangeMenuItemStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.order.ChangeOrderStatusRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateOrderController {
    private final ChangeOrderStatusUseCase orderStatusUseCase;
    private final MessageSource messageSource;

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> changeStatus(
            @PathVariable Long id,
            @RequestBody ChangeOrderStatusRequest request,
            Locale locale) {

        orderStatusUseCase.execute(id, request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("order.status_changed_success", null, locale))
                .build();
    }
}
