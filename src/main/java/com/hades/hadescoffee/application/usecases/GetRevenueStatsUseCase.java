package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.repositories.InvoicesRepository;
import com.hades.hadescoffee.presentation.dto.responses.RevenueStatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetRevenueStatsUseCase {
    private final InvoicesRepository repository;
    public RevenueStatsResponse execute() {
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate today = LocalDate.now(zoneId);

        Instant startOfDay = today.atStartOfDay(zoneId).toInstant();

        LocalDate startOfWeekDate = today.with(DayOfWeek.MONDAY);
        Instant startOfWeek = startOfWeekDate.atStartOfDay(zoneId).toInstant();

        LocalDate startOfMonthDate = today.withDayOfMonth(1);
        Instant startOfMonth = startOfMonthDate.atStartOfDay(zoneId).toInstant();

        BigDecimal dayRevenue = repository.sumRevenueDay(startOfDay);
        BigDecimal weekRevenue = repository.sumRevenueWeek(startOfWeek);
        BigDecimal monthRevenue = repository.sumRevenueMonth(startOfMonth);

        return new RevenueStatsResponse(dayRevenue, weekRevenue, monthRevenue);
    }
}
