package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.enums.RoleStatus;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AUserMapper {
    default Set<UserRole> setUserRoles(Set<UserRole> existingRoles, Role role) {
        existingRoles.add(new UserRole(null, role.getCode(), RoleStatus.ACTIVE));
        return existingRoles;
    }

    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "userRoles", expression = "java(setUserRoles(new java.util.HashSet<>(), role))")
    User signUpRequestToDomain(SignUpRequest request, String hashedPassword, Role role);
}
