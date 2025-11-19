package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import com.hades.hadescoffee.infrastructure.entities.OrderEntity;
import com.hades.hadescoffee.infrastructure.mappers.IInvoiceMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.InvoicesJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.OrdersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        //TODO: custom exception
        OrderEntity orderEntity = ordersJpaRepository.findById(invoice.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        InvoiceEntity invoiceEntity = mapper.modelToEntity(invoice, orderEntity);
        repository.save(invoiceEntity);
    }
}
