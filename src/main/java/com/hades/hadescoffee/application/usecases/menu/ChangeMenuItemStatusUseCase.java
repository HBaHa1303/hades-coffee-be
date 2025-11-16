package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.exceptions.ANotFoundException;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.ChangeMenuItemStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class ChangeMenuItemStatusUseCase {
    private final MenuItemsRepository menuItemsRepository;

    public void execute(Long menuItemId, ChangeMenuItemStatusRequest request){
        MenuItem menuItem = menuItemsRepository.findById(menuItemId)
                .orElseThrow(() -> new ANotFoundException("menu.not_found", menuItemId));
        menuItem.changeStatus(request.getStatus());
        menuItemsRepository.save(menuItem);
    }
}
