package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.presentation.dto.requests.category.CreateCategoryRequest;
import com.hades.hadescoffee.presentation.dto.responses.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ACategoryMapper {
    @Mapping(target = "status", source = "status")
    Category createRequestToDomain(CreateCategoryRequest createCategoryRequest, CategoryStatus status);
    CategoryResponse domainToResponse(Category category);
}
