package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRoleMapper {
    Role entityToModel(RoleEntity entity);
    RoleEntity modelToEntity(Role model);
}
