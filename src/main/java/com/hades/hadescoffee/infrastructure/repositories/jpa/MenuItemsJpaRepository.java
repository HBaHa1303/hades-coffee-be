package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsJpaRepository extends JpaRepository<MenuItemEntity, Long> {
}
