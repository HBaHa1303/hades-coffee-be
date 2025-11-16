package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;

import java.util.Optional;

public interface UserRolesRepository {
    void save(UserRole userRole);
}
