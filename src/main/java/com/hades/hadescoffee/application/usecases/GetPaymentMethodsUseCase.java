package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.PaymentMethod;
import com.hades.hadescoffee.presentation.dto.responses.PaymentMethodResponse;
import io.jsonwebtoken.lang.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetPaymentMethodsUseCase {

    public List<PaymentMethodResponse> getAllPaymentMethods() {
        return Arrays.asList(PaymentMethod.values()).stream()
                .map(value -> new PaymentMethodResponse(value.toString()))
                .toList();
    }
}
