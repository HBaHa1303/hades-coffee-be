package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITableMapper {
    Table entityToModel(TableEntity entity);
    TableEntity modelToEntity(Table model);
}
