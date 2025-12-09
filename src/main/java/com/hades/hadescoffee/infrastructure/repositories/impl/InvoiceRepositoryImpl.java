package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.mappers.IInvoiceMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.InvoicesJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.OrdersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoicesRepository {
    private final InvoicesJpaRepository repository;
    private final IInvoiceMapper mapper;
    private final OrdersJpaRepository ordersJpaRepository;

    @Override
    public Optional<Invoice> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Invoice invoice) {
        OrderEntity orderEntity = ordersJpaRepository.findById(invoice.getOrderId())
                .orElseThrow(() -> new ANotFoundException("order.not_found", invoice.getOrderId()));

        InvoiceEntity invoiceEntity = mapper.modelToEntity(invoice, orderEntity);
        repository.save(invoiceEntity);
    }

    @Override
    public BigDecimal sumRevenueWeek(Instant startOfWeek) {
        return repository.sumRevenueWeek(startOfWeek);
    }

    @Override
    public BigDecimal sumRevenueMonth(Instant startOfMonth) {
        return repository.sumRevenueMonth(startOfMonth);
    }

    @Override
    public BigDecimal sumRevenueDay(Instant startOfDay) {
        return repository.sumRevenueDay(startOfDay);
    }
}
