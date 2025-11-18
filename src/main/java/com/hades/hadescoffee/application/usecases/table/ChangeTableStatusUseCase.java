package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.presentation.dto.requests.tables.ChangeTableStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.tables.CreateTableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class ChangeTableStatusUseCase {
    private final TablesRepository tablesRepository;

    public void execute(Long id, ChangeTableStatusRequest request) {
        Table table = tablesRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("table.not_found", id));

        switch (request.getStatus()) {
            case AVAILABLE -> table.markAvailable();
            case OCCUPIED -> table.seatCustomer();
            case RESERVED -> table.reserve();
            case OUT_OF_SERVICE -> table.markOutOfService();
        }

        tablesRepository.save(table);
    }
}