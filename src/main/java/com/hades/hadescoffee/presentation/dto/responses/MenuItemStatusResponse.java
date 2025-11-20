package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.MenuItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuItemStatusResponse {
    MenuItemStatus status;
}

