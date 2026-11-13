package com.hades.hadescoffee.infrastructure.repositories.impl;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.infrastructure.mappers.IMenuItemMapper;
import com.hades.hadescoffee.infrastructure.repositories.mongo.CategoriesMongoRepository;
import com.hades.hadescoffee.infrastructure.repositories.mongo.MenuItemsMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuItemsRepositoryImpl implements MenuItemsRepository {

    private final MenuItemsMongoRepository repository;
    private final IMenuItemMapper mapper;
    private final CategoriesMongoRepository categoriesRepository;

    @Override
    public Optional<MenuItem> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void save(MenuItem menuItem) {
        CategoryEntity categoryEntity = categoriesRepository.findById(menuItem.getCategoryId())
                .orElseThrow(() ->
                        new ANotFoundException("category.not_found", menuItem.getCategoryId()));

        repository.save(
                mapper.modelToEntity(menuItem, categoryEntity)
        );
    }

    @Override
    public void delete(MenuItem menuItem) {
        repository.deleteById(menuItem.getId());
    }

    @Override
    public Paginated<MenuItem> findAllByKeyWordAndStatus(
            String keyword,
            MenuItemStatus status,
            Pageable pageable
    ) {
        String key = keyword == null ? "" : keyword;

        List<String> statusList = status == null
                ? Arrays.stream(MenuItemStatus.values()).map(Enum::name).toList()
                : List.of(status.name());

        Page<MenuItem> dbResponse = repository
                .findAllByKeywordAndStatus(key, statusList, pageable)
                .map(mapper::entityToModel);

        return Paginated.<MenuItem>builder()
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
    public List<MenuItem> findAllByCategoryId(Long categoryId) {
        return repository.findAllByCategoryId(categoryId)
                .stream()
                .map(mapper::entityToModel)
                .toList();
    }
}
