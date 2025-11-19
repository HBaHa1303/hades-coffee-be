package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.infrastructure.mappers.ICategoryMapper;
import com.hades.hadescoffee.infrastructure.repositories.jpa.CategoriesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoriesRepositoryImpl implements CategoriesRepository {
    private final CategoriesJpaRepository repository;
    private final ICategoryMapper mapper;

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void save(Category category) {
        repository.save(mapper.modelToEntity(category));
    }

    @Override
    public void delete(Category category) {
        repository.deleteById(category.getId());
    }

    @Override
    public Paginated<Category> findAll(Pageable pageable) {
        Page<Category> dbResponse = repository.findAll(pageable)
                .map(mapper::entityToModel);
        return Paginated.<Category>builder()
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

    @Override
    public Paginated<Category> findAllByKeyWord(String keyword, Pageable pageable) {
        Page<Category> dbResponse = repository.findByKeyword(keyword, pageable)
                .map(mapper::entityToModel);
        return Paginated.<Category>builder()
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

    @Override
    public Paginated<Category> findAllByKeyWordAndStatus(String keyword, CategoryStatus status, Pageable pageable) {
        Page<Category> dbResponse = repository.findByKeywordAndStatus(keyword, status, pageable)
                .map(mapper::entityToModel);
        return Paginated.<Category>builder()
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

    @Override
    public List<Category> findAllActive() {
        return repository.findAllActive(CategoryStatus.ACTIVE).stream()
                .map(mapper::entityToModel)
                .toList();
    }
}
