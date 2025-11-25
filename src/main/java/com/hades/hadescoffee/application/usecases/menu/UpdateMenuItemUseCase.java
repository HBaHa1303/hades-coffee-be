package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.UpdateMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;

    public void execute(Long id, UpdateMenuItemRequest request) {
        MenuItem menuItem = menuItemsRepository.findById(id)
                .orElseThrow(() -> new ANotFoundException("menu.not_found", id));
        menuItem.changeDescription(request.getDescription());
        menuItem.changePrice(request.getPrice());
        menuItem.changeImageUrl(request.getImageUrl());
        menuItem.changeName(request.getName());
        menuItemsRepository.save(menuItem);
    }
}
