package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import com.hades.hadescoffee.infrastructure.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TablesJpaRepository extends JpaRepository<TableEntity, Long> {
    @Query("""
    SELECT t FROM TableEntity t
    WHERE (:keyword IS NULL OR LOWER(t.name) LIKE concat('%', LOWER(:keyword), '%'))
      AND (:status IS NULL OR t.status = :status)
    """)
    Page<TableEntity> findAllByKeywordAndStatus(String keyword, TableStatus status, Pageable pageable);
}
