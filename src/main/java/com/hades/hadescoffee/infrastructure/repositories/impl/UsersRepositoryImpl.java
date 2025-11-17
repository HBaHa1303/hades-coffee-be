package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.infrastructure.mappers.IUserMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.RolesJpaRepository;
import com.hades.hadescoffee.infrastructure.repositories.jpa.UsersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    private final UsersJpaRepository usersRepository;
    private final IUserMapper mapper;
    private final RolesJpaRepository rolesRepository;

    @Override
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = mapper.modelToEntity(user);
//        userEntity.getUserRoles().stream()
//                .map(role -> rolesRepository.findByCode())
//        usersRepository.save();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(mapper::entityToModel);
    }
}
