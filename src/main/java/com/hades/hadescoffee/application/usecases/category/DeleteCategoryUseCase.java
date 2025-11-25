package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCategoryUseCase {
    private final CategoriesRepository categoriesRepository;

    public void execute(Long id) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("category.not_found", id));
        categoriesRepository.delete(category);
    }
}