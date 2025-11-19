package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import com.hades.hadescoffee.presentation.dto.requests.user.CreateUserRequest;
import com.hades.hadescoffee.presentation.dto.responses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AUserMapper {
    default Email mapEmail(String email) {
        return new Email(email);
    }
    default String mapEmail(Email email) {
        return email.getValue();
    }
    default Set<UserRole> addUserRole(UserRole userRole) {
        return Set.of(userRole);
    }
    default Set<UserRole> addUserRole(Set<UserRole> userRoles) {
        return userRoles;
    }
    default String mapUserRole(UserRole userRole) {
        return userRole.getRoleCode();
    }

    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "userRoles", expression = "java(addUserRole(userRole))")
    User signUpRequestToDomain(SignUpRequest request, String hashedPassword, UserRole userRole);

    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "userRoles", expression = "java(addUserRole(userRoles))")
    User createRequestToDomain(CreateUserRequest request, String hashedPassword, Set<UserRole> userRoles);

    @Mapping(target = "roles", source = "user.userRoles")
    UserResponse domainToResponse(User user);
}
