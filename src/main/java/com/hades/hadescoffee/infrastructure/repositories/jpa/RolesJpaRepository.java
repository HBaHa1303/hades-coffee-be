package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RolesJpaRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByCode(String code);

    Set<RoleEntity> findByCodeIn(Set<String> roleCodes);
}
