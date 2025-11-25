package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.presentation.dto.responses.OrderStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetOrderStatusesUseCase {

    public List<OrderStatusResponse> execute() {
        return Arrays.stream(OrderStatus.values())
                .map(OrderStatusResponse::new)
                .toList();
    }
}

