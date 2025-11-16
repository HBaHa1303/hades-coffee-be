package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.application.usecases.category.*;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfig {
    private final CategoriesRepository repository;
    private final ACategoryMapper mapper;

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new CreateCategoryUseCase(repository, mapper);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new UpdateCategoryUseCase(repository);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DeleteCategoryUseCase(repository);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new GetCategoryByIdUseCase(repository, mapper);
    }

    @Bean
    public GetCategoriesUseCase getCategoriesUseCase() {
        return new GetCategoriesUseCase(repository, mapper);
    }

    @Bean
    public ChangeCategoryStatusUseCase changeCategoryStatusUseCase() {
        return new ChangeCategoryStatusUseCase(repository);
    }
}
