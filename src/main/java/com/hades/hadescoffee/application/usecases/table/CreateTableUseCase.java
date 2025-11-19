package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.mappers.ATableMapper;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.presentation.dto.requests.tables.CreateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CreateTableUseCase {
    private final TablesRepository tablesRepository;
    private final ATableMapper mapper;

    public void execute(CreateTableRequest request) {
        tablesRepository.save(mapper.createRequestToDomain(request, TableStatus.AVAILABLE));
    }
}


