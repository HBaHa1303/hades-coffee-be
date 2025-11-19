package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMenuItemByIdUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final AMenuItemMapper mapper;

    public MenuItemResponse execute(Long id) {
        MenuItem menuItem = menuItemsRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("menu.not_found", id));

        return mapper.domainToResponse(menuItem);
    }
}
