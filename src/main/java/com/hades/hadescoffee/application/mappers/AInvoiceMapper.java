package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AInvoiceMapper {
    @Mapping(target = "status", expression = "java(InvoiceStatus.UNPAID)")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "amount", source = "amount")
    Invoice createOrderToModel(CreateOrderRequest request, Money amount, Long orderId);
}
