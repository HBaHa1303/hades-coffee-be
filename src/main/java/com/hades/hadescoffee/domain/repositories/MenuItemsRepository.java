package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MenuItemsRepository {
    Optional<MenuItem> findById(Long id);
    void save(MenuItem menuItem);
    void delete(MenuItem menuItem);
    List<MenuItem> findAll();
}
