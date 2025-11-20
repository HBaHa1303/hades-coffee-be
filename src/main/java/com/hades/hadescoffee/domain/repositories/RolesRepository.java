package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Role;

import java.util.List;
import java.util.Optional;

public interface RolesRepository {
    Optional<Role> findByCode(String code);
    void save(Role role);
    List<Role> findAll();
}
