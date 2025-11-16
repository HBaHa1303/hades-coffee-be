package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.mappers.MenuItemMapper;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.requests.CreateMenuItemRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final MenuItemMapper menuItemMapper;

    public void execute(CreateMenuItemRequest createMenuCommand) {
        menuItemsRepository.save(menuItemMapper.createReqestToDomain(createMenuCommand));
    }
}
