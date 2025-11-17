package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.infrastructure.mappers.IUserMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.RolesJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.UsersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    private final UsersJpaRepository usersRepository;
    private final IUserMapper mapper;
    private final RolesJpaRepository rolesJpaRepository;

    @Override
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public void save(User user) {
        Set<String> roleCodes = user.getUserRoles()
                .stream()
                .map(UserRole::getRoleCode)
                .collect(Collectors.toSet());
        Set<RoleEntity> roles = rolesJpaRepository.findByCodeIn(roleCodes);
        UserEntity userEntity = mapper.modelToEntity(user, roles);
        usersRepository.save(userEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(mapper::entityToModel);
    }

    @Override
    public Optional<User> findByEmailWithRoles(String email) {
        return usersRepository.findByEmailWithRoles(email).map(mapper::entityToModel);
    }
}
