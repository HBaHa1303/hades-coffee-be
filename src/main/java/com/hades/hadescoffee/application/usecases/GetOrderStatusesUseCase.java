package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.application.mappers.ARoleMapper;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.presentation.dto.responses.OrderStatusResponse;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetOrderStatusesUseCase {

    public List<OrderStatusResponse> execute() {
        return Arrays.stream(OrderStatus.values())
                .map(OrderStatusResponse::new)
                .toList();
    }
}

