package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoryByIdUseCase {
    private final CategoriesRepository categoriesRepository;
    private final ACategoryMapper mapper;

    public CategoryResponse execute(Long id) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("category.not_found", id));
        return mapper.domainToResponse(category);
    }
}