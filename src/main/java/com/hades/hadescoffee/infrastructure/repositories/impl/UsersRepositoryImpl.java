package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.models.UserRole;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import com.hades.hadescoffee.infrastructure.mappers.IUserMapper;
import com.hades.hadescoffee.infrastructure.repositories.mongo.RolesMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.UsersMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersMongoRepository usersRepository;
    private final RolesMongoRepository rolesRepository;
    private final IUserMapper mapper;

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<User> findById(Long id) {
        return usersRepository.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void save(User user) {
        Set<String> roleCodes = user.getRoleCodes();

        Set<RoleEntity> roles = rolesRepository.findByCodeIn(roleCodes);

        UserEntity entity = mapper.modelToEntity(user, roles);
        usersRepository.save(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .map(mapper::entityToModel);
    }

    @Override
    public Optional<User> findByIdWithRoles(Long id) {
        return usersRepository.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void delete(User user) {
        usersRepository.deleteById(user.getId());
    }

    @Override
    public Paginated<User> findAllByKeywordAndRole(String keyword, Pageable pageable) {
        String k = (keyword == null) ? "" : keyword;

        Page<User> dbResponse = usersRepository
                .findAllByKeyword(k, pageable)
                .map(mapper::entityToModel);

        return Paginated.<User>builder()
                .data(dbResponse.getContent())
                .paging(PagingResult.builder()
                        .size(dbResponse.getSize())
                        .page(dbResponse.getNumber())
                        .hasNext(dbResponse.hasNext())
                        .hasPrevious(dbResponse.hasPrevious())
                        .isFirst(dbResponse.isFirst())
                        .isLast(dbResponse.isLast())
                        .totalElements(dbResponse.getTotalElements())
                        .totalPages(dbResponse.getTotalPages())
                        .build())
                .build();
    }

    @Override
    public Set<User> findByIdIn(Set<Long> userIds) {
        return usersRepository.findByIdIn(userIds)
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toSet());
    }
}
