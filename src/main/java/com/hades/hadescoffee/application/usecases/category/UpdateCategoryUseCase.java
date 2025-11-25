package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.requests.categories.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateCategoryUseCase {
    private final CategoriesRepository categoriesRepository;

    public void execute(Long id, UpdateCategoryRequest request) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("category.not_found", id));

        category.rename(request.getName());
        categoriesRepository.save(category);
    }
}
