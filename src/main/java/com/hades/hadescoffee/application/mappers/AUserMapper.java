package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AUserMapper {
    default Set<UserRole> addUserRole(UserRole userRole) {
        return Set.of(userRole);
    }

    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "userRoles", expression = "java(addUserRole(userRole))")
    User signUpRequestToDomain(SignUpRequest request, String hashedPassword, UserRole userRole);
}
