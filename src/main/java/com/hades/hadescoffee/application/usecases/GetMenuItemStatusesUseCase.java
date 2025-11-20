package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetMenuItemStatusesUseCase {

    public List<MenuItemStatusResponse> execute() {
        return Arrays.stream(MenuItemStatus.values())
                .map(MenuItemStatusResponse::new)
                .toList();
    }
}
