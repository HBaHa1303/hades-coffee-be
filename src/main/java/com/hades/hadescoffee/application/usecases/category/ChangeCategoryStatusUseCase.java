package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.requests.category.ChangeCategoryStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChangeCategoryStatusUseCase {
    private final CategoriesRepository categoriesRepository;

    public void execute(Long categoryId, ChangeCategoryStatusRequest request) {
        Category category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ANotFoundException("category.not_found", categoryId));

        if (request.getStatus() == CategoryStatus.ACTIVE) {
            category.activate();
        } else {
            category.deactivate();
        }

        categoriesRepository.save(category);
    }
}
