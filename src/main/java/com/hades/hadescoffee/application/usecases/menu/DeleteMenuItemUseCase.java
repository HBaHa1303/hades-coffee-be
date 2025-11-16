package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class DeleteMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;

    public void execute(Long id) {
        MenuItem menuItem = menuItemsRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("menu.not_found", id));
        menuItemsRepository.delete(menuItem);
    }
}
