package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    @Query("""
        SELECT u FROM UserEntity u 
        LEFT JOIN FETCH u.userRoles ur 
        LEFT JOIN FETCH ur.role 
        WHERE u.email = :email
    """)
    Optional<UserEntity> findByEmailWithRoles(String email);

    @Query("""
        SELECT u FROM UserEntity u 
        LEFT JOIN FETCH u.userRoles ur 
        LEFT JOIN FETCH ur.role 
        WHERE u.id = :id
    """)
    Optional<UserEntity> findByIdWithRoles(Long id);

    @Query("""
        SELECT DISTINCT u FROM UserEntity u
        LEFT JOIN u.userRoles ur
        LEFT JOIN ur.role r
        WHERE  :keyword IS NULL OR LOWER(u.firstName) LIKE CONCAT('%', LOWER(:keyword), '%')
                    OR LOWER(u.lastName) LIKE CONCAT('%', LOWER(:keyword), '%')
    """)
    Page<UserEntity> findAllByKeywordAndRole(String keyword, Pageable pageable);
}
