package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.presentation.dto.requests.tables.UpdateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateTableUseCase {
    private final TablesRepository tablesRepository;

    public void execute(Long id, UpdateTableRequest request) {
        Table table = tablesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("table.not_found", id));
        table.changeName(request.getName());
        tablesRepository.save(table);
    }
}

