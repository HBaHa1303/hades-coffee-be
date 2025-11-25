package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.domain.models.Category;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.CategoriesRepository;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.presentation.dto.responses.MenuByCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMenuItemsByCategoryUseCase {
    private final CategoriesRepository categoryRepository;
    private final MenuItemsRepository menuItemsRepository;
    private final AMenuItemMapper menuItemMapper;

    public List<MenuByCategoryResponse> execute() {
        List<Category> categories = categoryRepository.findAllActive();
        return categories.stream()
                .map(cat -> {
            List<MenuItem> items = menuItemsRepository.findAllByCategoryId(cat.getId());

            return menuItemMapper.mapMenuByCategory(cat, items);
        }).toList();
    }
}

