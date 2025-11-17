package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.models.Category;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriesRepository {
    Optional<Category> findById(Long id);
    void save(Category category);
    void delete(Category category);
    Paginated<Category> findAll(Pageable pageable);
    Paginated<Category> findAllByKeyWord(String keyword, Pageable pageable);

    Paginated<Category> findAllByKeyWordAndStatus(String keyword, CategoryStatus status, Pageable pageable);
}
