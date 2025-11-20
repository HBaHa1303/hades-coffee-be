package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.menu.*;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.ChangeMenuItemStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.CreateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.FilterMenuItemsRequest;
import com.hades.hadescoffee.presentation.dto.requests.menuItems.UpdateMenuItemRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.MenuItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/menu-items",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PrivateMenuItemController {
    private final MessageSource messageSource;

    private final CreateMenuItemUseCase createUseCase;
    private final UpdateMenuItemUseCase updateUseCase;
    private final DeleteMenuItemUseCase deleteUseCase;
    private final GetMenuItemByIdUseCase getByIdUseCase;
    private final GetMenuItemUseCase getUseCase;
    private final ChangeMenuItemStatusUseCase changeStatusUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<Object> createMenuItem(
            @RequestBody CreateMenuItemRequest request,
            Locale locale) {

        createUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("menu.created_success", null, locale))
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<Object> updateMenu(
            @PathVariable Long id,
            @RequestBody UpdateMenuItemRequest request,
            Locale locale) {

        updateUseCase.execute(id, request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("menu.updated_success", null, locale))
                .build();
    }

    @PatchMapping("/{id}/status")
    public BaseResponse<Object> changeStatus(
            @PathVariable Long id,
            @RequestBody ChangeMenuItemStatusRequest request,
            Locale locale) {

        changeStatusUseCase.execute(id, request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("menu.status_changed_success", null, locale))
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> delete(
            @PathVariable Long id,
            Locale locale) {

        deleteUseCase.execute(id);

        return BaseResponse.builder()
                .message(messageSource.getMessage("menu.deleted_success", null, locale))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<MenuItemResponse> getById(
            @PathVariable Long id,
            Locale locale) {

        var data = getByIdUseCase.execute(id);

        return BaseResponse.<MenuItemResponse>builder()
                .data(data)
                .message(messageSource.getMessage("menu.get_by_id_success", null, locale))
                .build();
    }

    @GetMapping
    public BaseResponse<List<MenuItemResponse>> list(FilterMenuItemsRequest request, Locale locale) {

        var data = getUseCase.execute(request);

        return BaseResponse.<List<MenuItemResponse>>builder()
                .data(data.getData())
                .paging(data.getPaging())
                .message(messageSource.getMessage("menu.get_success", null, locale))
                .build();
    }
}
