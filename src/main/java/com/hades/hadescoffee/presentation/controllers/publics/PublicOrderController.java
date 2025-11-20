package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.order.CreateOrderUseCase;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "public/api/v1/orders",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicOrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> createOrder(@RequestBody CreateOrderRequest request, Locale locale) {
        createOrderUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("order.created_success", null, locale))
                .build();
    }


}
