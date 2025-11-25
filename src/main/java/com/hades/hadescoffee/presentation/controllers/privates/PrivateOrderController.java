package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.order.ChangeOrderStatusUseCase;
import com.hades.hadescoffee.application.usecases.order.GetOrdersUseCase;
import com.hades.hadescoffee.presentation.dto.requests.order.ChangeOrderStatusRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateOrderController {
    private final ChangeOrderStatusUseCase orderStatusUseCase;
    private final GetOrdersUseCase getOrdersUseCase;
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

    @GetMapping
    public BaseResponse<List<OrderResponse>> getOrders(Locale locale) {
        var data = getOrdersUseCase.execute();

        return BaseResponse.<List<OrderResponse>>builder()
                .data(data)
//                .paging(data.getPaging())
                .message(messageSource.getMessage("menu.get_success", null, locale))
                .build();
    }
}
