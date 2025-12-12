package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.mappers.ATableMapper;
import com.hades.hadescoffee.application.services.SequenceGeneratorService;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import com.hades.hadescoffee.presentation.dto.requests.table.CreateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTableUseCase {
    private final TablesRepository tablesRepository;
    private final ATableMapper mapper;
    private final SequenceGeneratorService sequenceGenerator;

    public void execute(CreateTableRequest request) {
        Long id = sequenceGenerator.generateSequence(TableEntity.SEQUENCE_NAME);
        Table table = mapper.createRequestToDomain(request, TableStatus.AVAILABLE, id);
        tablesRepository.save(table);
    }
}


