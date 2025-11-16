package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.presentation.dto.response.CategoryResponse;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriesRepository {
    Optional<Category> findById(Long id);
    void save(Category category);
    void delete(Category category);
    Paginated<Category> findAll(Pageable pageable);
}
