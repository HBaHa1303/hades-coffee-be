package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UsersMongoRepository extends MongoRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Set<UserEntity> findByIdIn(Set<Long> ids);

    @Query("""
    {
      "$or": [
        { "firstName": { "$regex": ?0, "$options": "i" } },
        { "lastName": { "$regex": ?0, "$options": "i" } },
        { "email": { "$regex": ?0, "$options": "i" } }
      ]
    }
    """)
    Page<UserEntity> findAllByKeyword(String keyword, Pageable pageable);
}