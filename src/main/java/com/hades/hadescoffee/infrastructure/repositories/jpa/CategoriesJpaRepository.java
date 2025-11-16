package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
