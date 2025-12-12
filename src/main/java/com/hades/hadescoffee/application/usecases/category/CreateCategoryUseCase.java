package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.application.services.SequenceGeneratorService;
import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.presentation.dto.requests.category.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCategoryUseCase {
    private final CategoriesRepository categoryRepository;
    private final ACategoryMapper mapper;
    private final SequenceGeneratorService sequenceGenerator;

    public void execute(CreateCategoryRequest request) {
        Long id = sequenceGenerator.generateSequence(CategoryEntity.SEQUENCE_NAME);
        Category category = mapper.createRequestToDomain(request, id, CategoryStatus.ACTIVE);
        categoryRepository.save(category);
    }
}