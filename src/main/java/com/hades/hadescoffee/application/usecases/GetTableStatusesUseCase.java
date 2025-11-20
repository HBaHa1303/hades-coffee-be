package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.application.mappers.ARoleMapper;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import com.hades.hadescoffee.presentation.dto.responses.TableStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTableStatusesUseCase {

    public List<TableStatusResponse> execute() {
        return Arrays.stream(TableStatus.values())
                .map(TableStatusResponse::new)
                .toList();
    }
}
