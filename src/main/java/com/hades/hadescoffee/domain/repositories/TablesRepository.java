package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;

import java.util.Optional;

public interface TablesRepository {
    Optional<Table> findByCode(String code);
    void save(Table table);
}
