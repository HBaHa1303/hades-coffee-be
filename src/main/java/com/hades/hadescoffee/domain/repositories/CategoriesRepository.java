package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Category;

import java.util.Optional;

public interface CategoriesRepository {
    Optional<Category> findById(Long id);
    void save(Category category);
}
