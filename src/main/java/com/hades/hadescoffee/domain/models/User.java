package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.enums.RoleStatus;
import com.hades.hadescoffee.domain.exceptions.DomainException;
import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.valueObjects.Email;
import com.hades.hadescoffee.domain.valueObjects.Password;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    final Long id;
    final Email email;
    Password password;
    final String firstName;
    final String lastName;
    final LocalDate dateOfBirth;
    final Set<UserRole> userRoles;

    public User(Long id, String email, String password, String firstName, String lastName,
                LocalDate dateOfBirth, Set<UserRole> userRoles) {
        if (StringUtils.isBlank(email)) {
            throw new DValidationException("common.invalid_input", "email");
        }
        if (StringUtils.isBlank(password)) {
            throw new DValidationException("common.invalid_input", "password");
        }
        if (StringUtils.isBlank(firstName)) {
            throw new DValidationException("common.invalid_input", "first_name");
        }
        if (StringUtils.isBlank(lastName)) {
            throw new DValidationException("common.invalid_input", "last_name");
        }
        if (Objects.isNull(dateOfBirth)) {
            throw new DValidationException("common.invalid_input", "date_of_birth");
        }

        this.id = id;
        this.email = new Email(email);
        this.password = new Password(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.userRoles = Objects.isNull(userRoles) ? new HashSet<>() : userRoles;
    }

    public void assignRole(Role role) {
        var existing = userRoles.stream()
                .filter(r -> r.getRoleCode().equals(role.getCode()))
                .findFirst();

        if (existing.isPresent()) {
            UserRole ur = existing.get();
            if (ur.getStatus() == RoleStatus.INACTIVE) {
                ur.activate();
            }
        } else {
            UserRole userRole = new UserRole(null, role.getCode(), RoleStatus.ACTIVE);
            userRoles.add(userRole);
        }
    }


    public void revokeRole(String roleCode) {
        var ur = userRoles.stream()
                .filter(r -> r.getRoleCode().equals(roleCode))
                .findFirst()
                .orElseThrow(() -> new DomainException("user.role_not_found", id, roleCode));
        ur.revoke();
    }

    public void changePassword(Password newPassword) {
        if (newPassword == null) throw new DValidationException("user.password_invalid", 6);
        this.password = newPassword;
    }
}
