package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ARoleMapper {
    RoleResponse modelToResponse(Role role);
}
