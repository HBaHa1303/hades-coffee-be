package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.response.CategoryResponse;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoriesUseCase {
    private final CategoriesRepository categoriesRepository;
    private final ACategoryMapper mapper;

    public Paginated<CategoryResponse> execute() {
        Paginated<Category> domainPage = categoriesRepository.findAll(Pageable.unpaged());
        List<CategoryResponse> responseList = domainPage.getData().stream()
                .map(mapper::domainToResponse)
                .toList();
        PagingResult paging = domainPage.getPaging();
        return new Paginated<>(responseList, paging);
    }
}
