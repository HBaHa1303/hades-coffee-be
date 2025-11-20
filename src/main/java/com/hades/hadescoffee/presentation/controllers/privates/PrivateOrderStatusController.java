package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetOrderStatusesUseCase;
import com.hades.hadescoffee.application.usecases.GetRolesUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.OrderStatusResponse;
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
@RequestMapping(
        value = "private/api/v1/order-statuses",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateOrderStatusController {

    private final GetOrderStatusesUseCase getOrderStatusesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<OrderStatusResponse>> getStatuses(Locale locale) {
        var data = getOrderStatusesUseCase.execute();

        return BaseResponse.<List<OrderStatusResponse>>builder()
                .message(messageSource.getMessage("orderStatus.get_success", null, locale))
                .data(data)
                .build();
    }
}
