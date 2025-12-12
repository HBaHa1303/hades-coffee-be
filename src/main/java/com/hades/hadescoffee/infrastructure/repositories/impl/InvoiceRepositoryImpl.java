package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.mappers.IInvoiceMapper;
import com.hades.hadescoffee.infrastructure.repositories.mongo.InvoicesMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.OrdersMongoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoicesRepository {

    private final InvoicesMongoRepository invoiceRepo;
    private final OrdersMongoRepository orderRepo;
    private final IInvoiceMapper mapper;

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepo.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void save(Invoice invoice) {
        OrderEntity orderEntity = orderRepo.findById(invoice.getOrderId())
                .orElseThrow(() ->
                        new ANotFoundException("order.not_found", invoice.getOrderId()));

        InvoiceEntity invoiceEntity = mapper.modelToEntity(invoice, orderEntity);
        invoiceRepo.save(invoiceEntity);
    }

    @Override
    public BigDecimal sumRevenueWeek(Instant startOfWeek) {
        Decimal128 result = invoiceRepo.sumRevenueFrom(startOfWeek);
        return result != null ? result.bigDecimalValue() : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal sumRevenueMonth(Instant startOfMonth) {
        Decimal128 result = invoiceRepo.sumRevenueFrom(startOfMonth);
        return result != null ? result.bigDecimalValue() : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal sumRevenueDay(Instant startOfDay) {
        Decimal128 result = invoiceRepo.sumRevenueFrom(startOfDay);
        return result != null ? result.bigDecimalValue() : BigDecimal.ZERO;
    }
}
