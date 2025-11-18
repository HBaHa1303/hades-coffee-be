package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.infrastructure.mappers.ITableMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.TablesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TablesRepositoryImpl implements TablesRepository {
    private final TablesJpaRepository repository;
    private final ITableMapper mapper;

    @Override
    public Optional<Table> findById(Long id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public void save(Table table) {
        repository.save(mapper.modelToEntity(table));
    }

    @Override
    public void delete(Table table) {
        repository.delete(mapper.modelToEntity(table));
    }

    @Override
    public Paginated<Table> findAllByKeywordAndStatus(String keyword, TableStatus status, Pageable pageable) {
        Page<Table> dbResponse = repository.findAllByKeywordAndStatus(keyword, status, pageable)
                .map(mapper::entityToModel);

        return Paginated.<Table>builder()
                .data(dbResponse.getContent())
                .paging(PagingResult.builder()
                        .size(dbResponse.getSize())
                        .page(dbResponse.getNumber())
                        .hasNext(dbResponse.hasNext())
                        .hasPrevious(dbResponse.hasPrevious())
                        .isFirst(dbResponse.isFirst())
                        .isLast(dbResponse.isLast())
                        .totalElements(dbResponse.getTotalElements())
                        .totalPages(dbResponse.getTotalPages())
                        .build())
                .build();
    }
}
