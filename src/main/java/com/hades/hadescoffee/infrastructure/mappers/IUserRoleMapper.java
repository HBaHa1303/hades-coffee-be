package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.infrastructure.entities.UserRoleEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = IRoleMapper.class)
public interface IUserRoleMapper {

    UserRole entityToModel(UserRoleEntity entity);

    UserRoleEntity modelToEntity(UserRole model);
}

