package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TablesRepositoryImpl implements TablesRepository {
    private final TablesRepository tablesRepository;

    @Override
    public Optional<Table> findByCode(String code) {
        return Optional.empty();
    }

    @Override
    public void save(Table table) {

    }
}
