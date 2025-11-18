package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TablesRepository {
    Optional<Table> findById(Long id);
    void save(Table table);
    void delete(Table table);
    Paginated<Table> findAllByKeywordAndStatus(String keyword, TableStatus status, Pageable pageable);

}
