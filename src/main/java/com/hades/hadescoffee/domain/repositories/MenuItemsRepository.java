package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.MenuItem;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MenuItemsRepository {
    Optional<MenuItem> findById(Long id);
    void save(MenuItem menuItem);
    void delete(MenuItem menuItem);
    Paginated<MenuItem> findAll(Pageable pageable);

    Paginated<MenuItem> findAllByKeyWordAndStatus(String keyword, MenuItemStatus status, Pageable pageable);

    List<MenuItem> findAllByCategoryId(Long categoryId);
}
