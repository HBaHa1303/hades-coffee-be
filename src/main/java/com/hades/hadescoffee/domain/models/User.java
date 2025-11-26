package com.hades.hadescoffee.domain.models;

import com.hades.hadescoffee.domain.exceptions.DValidationException;
import com.hades.hadescoffee.domain.vo.Email;
import com.hades.hadescoffee.domain.vo.Password;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseDomain {
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

    public void changeRoles(Set<String> newUserRoles) {
        if (Objects.isNull(userRoles)) {
            throw new DValidationException("common.invalid_input", "user role");
        }

        newUserRoles.forEach(nur -> {
            Optional<UserRole> existing = this.userRoles.stream()
                    .filter(r -> r.getRoleCode().equals(nur))
                    .findFirst();

            if (existing.isEmpty()) {
                this.userRoles.add(new UserRole(null, nur));
            }
        });

        userRoles.removeIf(ur -> !newUserRoles.contains(ur.getRoleCode()));
    }

    public void changePassword(Password newPassword) {
        if (newPassword == null) throw new DValidationException("user.password_invalid", 6);
        this.password = newPassword;
    }
}
