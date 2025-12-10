package com.hades.hadescoffee.application.usecases.order;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AOrderMapper;
import com.hades.hadescoffee.domain.enums.OrderStatus;
import com.hades.hadescoffee.domain.models.Order;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.OrdersRepository;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GetOrdersUseCase {
    private final OrdersRepository repository;
    private final AOrderMapper mapper;
    private final UsersRepository usersRepository;
    private final TablesRepository tablesRepository;

    public List<OrderResponse> execute(List<OrderStatus> statuses, UserDetails user) {

        List<Order> orders = user.getAuthorities().contains(
                new SimpleGrantedAuthority("USER"))
                ? repository.findAllByUserId(Long.parseLong(user.getUsername()))
                : repository.findByStatuses(statuses);

        // 1) Collect IDs
        Set<Long> userIds = orders.stream().map(Order::getUserId).collect(Collectors.toSet());
        Set<Long> tableIds = orders.stream().map(Order::getTableId).collect(Collectors.toSet());

        // 2) Batch query
        Map<Long, User> users = usersRepository.findByIdIn(userIds)
                .stream().collect(Collectors.toMap(User::getId, u -> u));

        Map<Long, Table> tables = tablesRepository.findByIdIn(tableIds)
                .stream().collect(Collectors.toMap(Table::getId, t -> t));

        // 3) Map responses
        return orders.stream()
                .map(order -> mapper.modelToResponse(
                        order,
                        users.get(order.getUserId()),
                        tables.get(order.getTableId())
                ))
                .toList();
    }

}
