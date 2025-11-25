package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.order.CreateOrderUseCase;
import com.hades.hadescoffee.application.usecases.order.GetOrderByIdUseCase;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import com.hades.hadescoffee.presentation.dto.responses.TableResponse;
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
    private final GetOrderByIdUseCase getOrderByIdUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> createOrder(@RequestBody CreateOrderRequest request, Locale locale) {
        createOrderUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("order.created_success", null, locale))
                .build();
    }


    @GetMapping("/{id}")
    public BaseResponse<OrderResponse> getById(@PathVariable Long id, Locale locale) {
        var data = getOrderByIdUseCase.execute(id);
        return BaseResponse.<OrderResponse>builder()
                .data(data)
                .message(messageSource.getMessage("table.get_by_id_success", null, locale))
                .build();
    }
}
