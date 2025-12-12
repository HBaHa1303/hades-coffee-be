package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.vo.Email;
import com.hades.hadescoffee.domain.vo.Password;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {IUserRoleMapper.class})
public interface IUserMapper {
    default Email mapEmail(String email) {
        return new Email(email);
    }
    default String mapEmail(Email email) {
        return email.getValue();
    }
    default Password mapPassword(String password) {
        return new Password(password);
    }
    default String mapPassword(Password password) {
        return password.getHashed();
    }

    User entityToModel(UserEntity entity);

//    @Mapping(target = "userRoles", expression = "java(mapUserRoles(userEntity, user, roles))")
    UserEntity modelToEntity(User user, Set<RoleEntity> roles);
}
