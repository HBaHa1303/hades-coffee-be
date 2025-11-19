package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesJpaRepository extends JpaRepository<InvoiceEntity, Long> {
}
