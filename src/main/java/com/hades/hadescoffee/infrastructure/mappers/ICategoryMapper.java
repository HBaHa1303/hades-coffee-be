package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICategoryMapper {
    Category entityToModel(CategoryEntity entity);
    CategoryEntity modelToEntity(Category model);
}
