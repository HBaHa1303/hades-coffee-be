package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MenuItemsRepository {
    Optional<MenuItem> findById(Long id);
    void save(MenuItem menuItem);
    void delete(MenuItem menuItem);
    Paginated<MenuItem> findAll(Pageable pageable);
}
