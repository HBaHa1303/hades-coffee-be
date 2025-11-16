package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.MenuItemMapper;
import com.hades.hadescoffee.application.usecases.menu.*;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MenuItemBeanConfig {
    private final MenuItemsRepository menuItemsRepository;
    private final MenuItemMapper menuItemMapper;

    @Bean
    public CreateMenuItemUseCase createMenuItemUseCase() {
        return new CreateMenuItemUseCase(menuItemsRepository, menuItemMapper);
    }

    @Bean
    public ChangeMenuItemStatusUseCase changeMenuItemStatusUseCase() {
        return new ChangeMenuItemStatusUseCase(menuItemsRepository);
    }

    @Bean
    public UpdateMenuItemUseCase updateMenuItemUseCase() {
        return new UpdateMenuItemUseCase(menuItemsRepository, menuItemMapper);
    }

    @Bean
    public GetMenuItemByIdUseCase getMenuItemByIdUseCase() {
        return new GetMenuItemByIdUseCase(menuItemsRepository, menuItemMapper);
    }

    @Bean
    public GetMenuItemUseCase getMenuItemUseCase() {
        return new GetMenuItemUseCase(menuItemsRepository, menuItemMapper);
    }

    @Bean
    public DeleteMenuItemUseCase deleteMenuItemUseCase() {
        return new DeleteMenuItemUseCase(menuItemsRepository);
    }
}
