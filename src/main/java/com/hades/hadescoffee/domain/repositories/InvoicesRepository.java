package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.Invoice;

import java.util.Optional;

public interface InvoicesRepository {
    Optional<Invoice> findById(Long id);
    void save(Invoice invoice);
}
