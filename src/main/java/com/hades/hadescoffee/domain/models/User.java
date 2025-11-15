package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.RoleStatus;
import com.hades.hadescoffee.domain.exceptions.DomainValidationException;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.infrastructure.entities.UserRoleEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    final Email email;
    String password;
    final String firstName;
    final String lastName;
    final LocalDate dateOfBirth;
    RoleStatus status;
    List<UserRoleEntity> userRoles;

    public User(Long id, Email email, String password, String firstName, String lastName, LocalDate dateOfBirth,
                RoleStatus status, List<UserRoleEntity> userRoles) {
        if (Objects.isNull(email)) {
            throw new DomainValidationException("common.invalid_input", "email");
        }
        if (StringUtils.isBlank(password)) {
            throw new DomainValidationException("common.invalid_input", "password");
        }
        if (StringUtils.isBlank(firstName)) {
            throw new DomainValidationException("common.invalid_input", "first_name");
        }
        if (StringUtils.isBlank(lastName)) {
            throw new DomainValidationException("common.invalid_input", "last_name");
        }
        if (Objects.isNull(dateOfBirth)) {
            throw new DomainValidationException("common.invalid_input", "date_of_birth");
        }
        if (Objects.isNull(status)) {
            throw new DomainValidationException("common.invalid_input", "status");
        }
        if (Objects.isNull(userRoles)) {
            throw new DomainValidationException("common.invalid_input", "user_roles");
        }

        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.userRoles = userRoles;
    }
}
