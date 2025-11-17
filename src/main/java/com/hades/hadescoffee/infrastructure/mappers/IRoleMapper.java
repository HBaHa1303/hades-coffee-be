package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.domain.valueObjects.Password;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRoleMapper {
    Role entityToModel(RoleEntity entity);
    RoleEntity modelToEntity(Role model);
}
