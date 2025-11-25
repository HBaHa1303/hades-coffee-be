package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AInvoiceMapper;
import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.OrderItem;
import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.domain.vo.Money;
import com.hades.hadescoffee.infrastructure.security.UserDetailsImpl;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderItemRequest;
import com.hades.hadescoffee.presentation.dto.requests.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;
    private final MenuItemsRepository menuItemsRepository;
    private final InvoicesRepository invoicesRepository;
    private final AInvoiceMapper invoiceMapper;
    private static final String SYSTEM_USER_ID = "1";

    public void execute(CreateOrderRequest request) {
        String userId;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl principalUser) {
            userId = principalUser.getUsername();
        } else {
            userId = SYSTEM_USER_ID;
        }

        List<OrderItem> orderItems = request.getOrderItems().stream().map(this::createOrderToModel).toList();
        Order order = mapper.createOrderByOrderItemsAndTableIdRequest(orderItems, request.getTableId(), userId);

        Order savedOrder = repository.save(order);

        Money amount = savedOrder.getOrderItems().stream()
                .map(orderItem -> orderItem.getPrice().multiply(orderItem.getQuantity()))
                .reduce(Money.of(BigDecimal.ZERO), Money::add);
        Invoice invoice = invoiceMapper.createOrderToModel(request, amount, savedOrder.getId());
        invoicesRepository.save(invoice);
    }

    public OrderItem createOrderToModel(CreateOrderItemRequest req) {
        MenuItem menu = menuItemsRepository.findById(req.getMenuItemId())
                .orElseThrow(() -> new ANotFoundException("menu.not_found", req.getMenuItemId()));

        return new OrderItem(null, menu.getId(), menu.getPrice(), req.getQuantity());
    }
}
