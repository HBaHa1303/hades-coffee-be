package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import com.hades.hadescoffee.infrastructure.mappers.IMenuItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuItemsJpaRepository extends JpaRepository<MenuItemEntity, Long> {
    @Query("""
    SELECT mi FROM MenuItemEntity mi
    WHERE (:keyword IS NULL OR LOWER(mi.name) LIKE concat('%', LOWER(:keyword), '%'))
      AND (:status IS NULL OR mi.status = :status)
    """)
    Page<MenuItemEntity> findAllByKeyWordAndStatus(String keyword, MenuItemStatus status, Pageable pageable);
}
