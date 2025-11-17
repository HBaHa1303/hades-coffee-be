package com.hades.hadescoffee.presentation.controllers;

import com.hades.hadescoffee.application.usecases.category.*;
import com.hades.hadescoffee.presentation.dto.requests.categories.FilterCategoriesRequest;
import com.hades.hadescoffee.presentation.dto.requests.categories.CreateCategoryRequest;
import com.hades.hadescoffee.presentation.dto.requests.categories.ChangeCategoryStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.categories.UpdateCategoryRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final MessageSource messageSource;

    private final CreateCategoryUseCase createUseCase;
    private final UpdateCategoryUseCase updateUseCase;
    private final DeleteCategoryUseCase deleteUseCase;
    private final GetCategoryByIdUseCase getByIdUseCase;
    private final GetCategoriesUseCase getUseCase;
    private final ChangeCategoryStatusUseCase changeStatusUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<Object> createCategory(
            @RequestBody CreateCategoryRequest request,
            Locale locale) {

        createUseCase.execute(request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("category.created_success", null, locale))
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<Object> updateMenu(
            @PathVariable Long id,
            @RequestBody UpdateCategoryRequest request,
            Locale locale) {

        updateUseCase.execute(id, request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("category.updated_success", null, locale))
                .build();
    }

    @PatchMapping("/{id}/status")
    public BaseResponse<Object> changeStatus(
            @PathVariable Long id,
            @RequestBody ChangeCategoryStatusRequest request,
            Locale locale) {

        changeStatusUseCase.execute(id, request);

        return BaseResponse.builder()
                .message(messageSource.getMessage("category.status_changed_success", null, locale))
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> delete(
            @PathVariable Long id,
            Locale locale) {

        deleteUseCase.execute(id);

        return BaseResponse.builder()
                .message(messageSource.getMessage("category.deleted_success", null, locale))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<CategoryResponse> getById(
            @PathVariable Long id,
            Locale locale) {

        var data = getByIdUseCase.execute(id);

        return BaseResponse.<CategoryResponse>builder()
                .data(data)
                .message(messageSource.getMessage("category.get_by_id_success", null, locale))
                .build();
    }

    @GetMapping
    public BaseResponse<List<CategoryResponse>> list(FilterCategoriesRequest request, Locale locale) {

        var data = getUseCase.execute(request);

        return BaseResponse.<List<CategoryResponse>>builder()
                .data(data.getData())
                .paging(data.getPaging())
                .message(messageSource.getMessage("category.get_success", null, locale))
                .build();
    }
}
