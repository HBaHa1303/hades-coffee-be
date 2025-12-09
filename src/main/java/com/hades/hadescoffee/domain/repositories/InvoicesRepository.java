package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Invoice;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

public interface InvoicesRepository {
    Optional<Invoice> findById(Long id);
    void save(Invoice invoice);
    BigDecimal sumRevenueWeek(Instant startOfWeek);
    BigDecimal sumRevenueMonth(Instant startOfMonth);
    BigDecimal sumRevenueDay(Instant startOfDay);
}
