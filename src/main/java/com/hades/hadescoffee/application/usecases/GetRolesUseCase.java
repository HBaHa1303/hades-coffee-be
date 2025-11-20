package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.application.mappers.ARoleMapper;
import com.hades.hadescoffee.domain.repositories.RolesRepository;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetRolesUseCase {
    private final RolesRepository repository;
    private final ARoleMapper mapper;

    public List<RoleResponse> execute() {
        return repository.findAll().stream()
                .map(mapper::modelToResponse)
                .toList();
    }
}
