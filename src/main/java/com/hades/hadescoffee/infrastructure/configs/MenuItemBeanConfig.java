package com.hades.hadescoffee.infrastructure.configs;

import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.application.usecases.menu.*;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MenuItemBeanConfig {
    private final MenuItemsRepository repository;
    private final AMenuItemMapper mapper;

    @Bean
    public CreateMenuItemUseCase createMenuItemUseCase() {
        return new CreateMenuItemUseCase(repository, mapper);
    }

    @Bean
    public ChangeMenuItemStatusUseCase changeMenuItemStatusUseCase() {
        return new ChangeMenuItemStatusUseCase(repository);
    }

    @Bean
    public UpdateMenuItemUseCase updateMenuItemUseCase() {
        return new UpdateMenuItemUseCase(repository);
    }

    @Bean
    public GetMenuItemByIdUseCase getMenuItemByIdUseCase() {
        return new GetMenuItemByIdUseCase(repository, mapper);
    }

    @Bean
    public GetMenuItemUseCase getMenuItemUseCase() {
        return new GetMenuItemUseCase(repository, mapper);
    }

    @Bean
    public DeleteMenuItemUseCase deleteMenuItemUseCase() {
        return new DeleteMenuItemUseCase(repository);
    }
}
