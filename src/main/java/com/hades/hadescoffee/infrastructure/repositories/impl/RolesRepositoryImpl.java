package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.infrastructure.mappers.IRoleMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.RolesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RolesRepositoryImpl implements RolesRepository {
    private final RolesJpaRepository repository;
    private final IRoleMapper mapper;

    @Override
    public Optional<Role> findByCode(String code) {
        return repository.findByCode(code).map(mapper::entityToModel);
    }

    @Override
    public void save(Role role) {
        repository.save(mapper.modelToEntity(role));
    }
}
