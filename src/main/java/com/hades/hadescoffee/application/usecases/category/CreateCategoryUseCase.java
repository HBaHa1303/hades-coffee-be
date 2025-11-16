package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.requests.categories.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CreateCategoryUseCase {
    private final CategoriesRepository categoryRepository;
    private final ACategoryMapper ACategoryMapper;

    public void execute(CreateCategoryRequest request) {
        categoryRepository.save(ACategoryMapper.createRequestToDomain(request));
    }
}