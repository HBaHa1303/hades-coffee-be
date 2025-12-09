package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public interface InvoicesJpaRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM InvoiceEntity i
        WHERE i.createdTime >= :startOfDay AND i.status IN ('PAID', 'UNPAID')
    """)
    BigDecimal sumRevenueDay(Instant startOfDay);

    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM InvoiceEntity i
        WHERE i.createdTime >= :startOfWeek AND i.status IN ('PAID', 'UNPAID')
    """)
    BigDecimal sumRevenueWeek(Instant startOfWeek);

    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM InvoiceEntity i
        WHERE i.createdTime >= :startOfMonth AND i.status IN ('PAID', 'UNPAID')
    """)
    BigDecimal sumRevenueMonth(Instant startOfMonth);
}
