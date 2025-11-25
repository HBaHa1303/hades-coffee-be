package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.PaymentMethod;
import com.hades.hadescoffee.presentation.dto.responses.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetPaymentMethodsUseCase {

    public List<PaymentMethodResponse> execute() {
        return Arrays.stream(PaymentMethod.values())
                .map(value -> new PaymentMethodResponse(value.toString()))
                .toList();
    }
}
