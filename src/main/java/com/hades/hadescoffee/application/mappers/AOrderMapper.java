package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AOrderMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Order createOrderToModel(CreateOrderRequest order);
}
