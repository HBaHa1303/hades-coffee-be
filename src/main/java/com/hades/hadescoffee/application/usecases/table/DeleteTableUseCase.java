package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class DeleteTableUseCase {
    private final TablesRepository tablesRepository;

    public void execute(Long id) {
        Table table = tablesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("table.not_found", id));
        tablesRepository.delete(table);
    }
}


