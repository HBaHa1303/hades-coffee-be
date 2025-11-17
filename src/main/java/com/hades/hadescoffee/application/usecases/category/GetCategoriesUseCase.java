package com.hades.hadescoffee.application.usecases.category;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.mappers.ACategoryMapper;
import com.hades.hadescoffee.application.utils.PageableUtil;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.presentation.dto.requests.categories.FilterCategoriesRequest;
import com.hades.hadescoffee.presentation.dto.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoriesUseCase {
    private final CategoriesRepository categoriesRepository;
    private final ACategoryMapper mapper;

    public Paginated<CategoryResponse> execute(FilterCategoriesRequest request) {
        Pageable pageable = PageableUtil.toPageable(request);
        Paginated<Category> domainPage = categoriesRepository.findAllByKeyWordAndStatus(
                request.getKeyword(), request.getStatus(), pageable);
        List<CategoryResponse> responseList = domainPage.getData().stream()
                .map(mapper::domainToResponse)
                .toList();
        PagingResult paging = domainPage.getPaging();
        return new Paginated<>(responseList, paging);
    }
}
