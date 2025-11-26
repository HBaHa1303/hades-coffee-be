package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.application.utils.PageableUtil;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.requests.menuitem.FilterMenuItemsRequest;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final AMenuItemMapper mapper;

    public Paginated<MenuItemResponse> execute(FilterMenuItemsRequest request) {
        Pageable pageable = PageableUtil.toPageable(request);
        Paginated<MenuItem> domainPage = menuItemsRepository.findAllByKeyWordAndStatus(
                request.getKeyword(), request.getStatus(), pageable);
        List<MenuItemResponse> responseList = domainPage.getData().stream()
                .map(mapper::domainToResponse)
                .toList();
        PagingResult paging = domainPage.getPaging();
        return new Paginated<>(responseList, paging);
    }
}
