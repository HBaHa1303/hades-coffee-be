package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.order.CreateOrderUseCase;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("public/api/v1/orders")
public class PublicOrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final MessageSource messageSource;

    @PostMapping
    public BaseResponse<Object> createOrder(@RequestBody CreateOrderRequest request, Locale locale) {
        createOrderUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("order.created_success", null, locale))
                .build();
    }
}
