package com.hades.hadescoffee.presentation.controllers.publics;

import com.hades.hadescoffee.application.usecases.GetPaymentMethodsUseCase;
import com.hades.hadescoffee.domain.enums.PaymentMethod;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("public/api/v1/payment-methods")
public class PublicPaymentMethodController {
    private final GetPaymentMethodsUseCase getPaymentMethodsUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<PaymentMethodResponse>> getPaymentMethods(Locale locale) {
        List<PaymentMethodResponse> response = getPaymentMethodsUseCase.getAllPaymentMethods();

        return BaseResponse.<List<PaymentMethodResponse>>builder()
                .message(messageSource.getMessage("payment_method.list_success", null, locale))
                .data(response)
                .build();
    }
}
