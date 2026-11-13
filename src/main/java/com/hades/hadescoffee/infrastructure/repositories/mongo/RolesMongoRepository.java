package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.infrastructure.entities.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

public interface RolesMongoRepository extends MongoRepository<RoleEntity, String> {
    Optional<RoleEntity> findByCode(String code);

    Set<RoleEntity> findByCodeIn(Set<String> roleCodes);
}
