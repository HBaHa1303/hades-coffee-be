package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.domain.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TableRepositoryImpl implements TableRepository {
    private final TableRepository tableRepository;
}
