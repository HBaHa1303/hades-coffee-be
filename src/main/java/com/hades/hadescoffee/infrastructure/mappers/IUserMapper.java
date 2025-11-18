package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.domain.valueObjects.Password;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.infrastructure.entities.UserRoleEntity;
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

    @Mapping(target = "userRoles", expression = "java(entity.userRoles.stream().map().collect(Collectors.toSet()))")
    User entityToModel(UserEntity entity);

    @Mapping(target = "userRoles", expression = "java(mapUserRoles(userEntity, user, roles))")
    UserEntity modelToEntity(User user, Set<RoleEntity> roles);

    default Set<UserRoleEntity> mapUserRoles(UserEntity userEntity,
                                             User user,
                                             Set<RoleEntity> roles) {

        Map<String, RoleEntity> roleMap = roles.stream()
                .collect(Collectors.toMap(RoleEntity::getCode, r -> r));

        return user.getUserRoles().stream()
                .map(ur -> {
                    UserRoleEntity e = new UserRoleEntity();
                    e.setId(ur.getId());
                    e.setUser(userEntity);
                    e.setRole(roleMap.get(ur.getRoleCode()));
                    return e;
                })
                .collect(Collectors.toSet());
    }
}
