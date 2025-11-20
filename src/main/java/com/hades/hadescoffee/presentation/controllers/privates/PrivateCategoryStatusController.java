package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetCategoryStatusesUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.CategoryStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/category-statuses", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateCategoryStatusController {
    private final GetCategoryStatusesUseCase getCategoryStatusesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<CategoryStatusResponse>> getCategories(Locale locale) {
        List<CategoryStatusResponse> response = getCategoryStatusesUseCase.execute();

        return BaseResponse.<List<CategoryStatusResponse>>builder()
                .message(messageSource.getMessage("categoryStatus.get_success", null, locale))
                .data(response)
                .build();
    }
}
