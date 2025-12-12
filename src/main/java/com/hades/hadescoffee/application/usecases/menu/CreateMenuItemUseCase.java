package com.hades.hadescoffee.application.usecases.menu;

import com.hades.hadescoffee.application.mappers.AMenuItemMapper;
import com.hades.hadescoffee.application.services.SequenceGeneratorService;
import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.domain.models.MenuItem;
import com.hades.hadescoffee.domain.repositories.MenuItemsRepository;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import com.hades.hadescoffee.presentation.dto.requests.menuitem.CreateMenuItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateMenuItemUseCase {
    private final MenuItemsRepository menuItemsRepository;
    private final AMenuItemMapper mapper;
    private final SequenceGeneratorService sequenceGenerator;

    public void execute(CreateMenuItemRequest createMenuCommand) {
        Long id = sequenceGenerator.generateSequence(CategoryEntity.SEQUENCE_NAME);
        MenuItem menuItem = mapper.createRequestToDomain(createMenuCommand, id,MenuItemStatus.AVAILABLE);
        menuItemsRepository.save(menuItem);
    }
}
