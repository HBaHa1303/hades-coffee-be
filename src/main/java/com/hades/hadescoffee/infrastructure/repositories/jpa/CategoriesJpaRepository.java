package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.mappers.IMenuItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesJpaRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("""
        SELECT c FROM CategoryEntity c
        WHERE :keyword IS NULL OR LOWER(c.name) LIKE concat('%', LOWER(:keyword),'%')
    """)
    Page<CategoryEntity> findByKeyword(String keyword, Pageable pageable);

    @Query("""
    SELECT c FROM CategoryEntity c
    WHERE (:keyword IS NULL OR LOWER(c.name) LIKE concat('%', LOWER(:keyword), '%'))
      AND (:status IS NULL OR c.status = :status)
    """)
    Page<CategoryEntity> findByKeywordAndStatus(String keyword, CategoryStatus status, Pageable pageable);

    @Query("SELECT c FROM CategoryEntity c WHERE c.status = :status")
    List<CategoryEntity> findAllActive(CategoryStatus status);
}
