package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.userRoles ur LEFT JOIN FETCH ur.role WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithRoles(String email);
}
