package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = IRoleMapper.class)
public interface IUserRoleMapper {
//    @Mapping(target = "roleCode", source = "role.code")
//    UserRole toDomain(UserRoleEntity entity);

}

