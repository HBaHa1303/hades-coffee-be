package com.hades.hadescoffee.infrastructure.repositories.jpa;

import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableJpaRepository extends JpaRepository<TableEntity, Long> {
}
