package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.presentation.dto.requests.categories.CreateCategoryRequest;
import com.hades.hadescoffee.presentation.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ACategoryMapper {
    Category createRequestToDomain(CreateCategoryRequest createCategoryRequest);
    CategoryResponse domainToResponse(Category Category);
}
