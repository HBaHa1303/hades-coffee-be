package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.mappers.MenuItemMapper;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GetMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final MenuItemMapper mapper;

    public Paginated<MenuItemResponse> execute() {
        return menuItemsRepository.findAll();
    }
}
