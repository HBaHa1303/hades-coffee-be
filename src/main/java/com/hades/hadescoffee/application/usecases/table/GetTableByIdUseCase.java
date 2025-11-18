package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.ATableMapper;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.presentation.dto.requests.tables.UpdateTableRequest;
import com.hades.hadescoffee.presentation.dto.responses.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTableByIdUseCase {
    private final TablesRepository tablesRepository;
    private final ATableMapper mapper;

    public TableResponse execute(Long id) {
        Table table = tablesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("table.not_found", id));
        return mapper.domainToResponse(table);
    }
}

