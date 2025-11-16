package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.mappers.MenuItemMapper;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final MenuItemMapper mapper;

    public List<MenuItemResponse> execute() {
        return menuItemsRepository.findAll()
                .stream()
                .map(mapper::domainToResponse).toList();
    }
}
