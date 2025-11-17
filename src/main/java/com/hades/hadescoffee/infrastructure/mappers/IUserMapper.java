package com.hades.hadescoffee.infrastructure.mappers;

import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.domain.valueObjects.Password;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

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
    UserEntity modelToEntity(User model);
}
