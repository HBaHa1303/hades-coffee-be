package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.mappers.AInvoiceMapper;
import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.domain.valueObjects.Money;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;
    private final InvoicesRepository invoicesRepository;
    private final AInvoiceMapper invoiceMapper;

    public void execute(CreateOrderRequest request) {
        Order order = mapper.createOrderToModel(request);
        Order savedOrder = repository.save(order);

        Money amount = savedOrder.getOrderItems().stream()
                .map(orderItem -> orderItem.getPrice().multiply(orderItem.getQuantity()))
                .reduce(Money.of(BigDecimal.ZERO), Money::add);
        Invoice invoice = invoiceMapper.createOrderToModel(request, amount, savedOrder.getId());
        invoicesRepository.save(invoice);
    }
}
