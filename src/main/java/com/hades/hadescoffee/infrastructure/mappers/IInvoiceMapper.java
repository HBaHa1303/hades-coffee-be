package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IInvoiceMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "id", source = "invoice.id")
    @Mapping(target = "status", source = "invoice.status")
    @Mapping(target = "amount", expression = "java(invoice.getAmount().getAmount())")
    InvoiceEntity modelToEntity(Invoice invoice, OrderEntity order);

    Invoice entityToModel(InvoiceEntity invoice);

    default Money map(BigDecimal value) {
        return new Money(value);
    }
}
