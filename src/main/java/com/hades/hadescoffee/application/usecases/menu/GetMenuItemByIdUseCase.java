package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.MenuItemMapper;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.response.MenuItemResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetMenuItemByIdUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemResponse execute(Long id) {
        MenuItem menuItem = menuItemsRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("menu.not_found", id));

        return menuItemMapper.domainToResponse(menuItem);
    }
}
