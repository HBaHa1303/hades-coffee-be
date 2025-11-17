package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.CreateMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CreateMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final AMenuItemMapper mapper;

    public void execute(CreateMenuItemRequest createMenuCommand) {
        menuItemsRepository.save(mapper.createRequestToDomain(createMenuCommand));
    }
}
