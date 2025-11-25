package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.presentation.dto.responses.CategoryStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoryStatusesUseCase {

    public List<CategoryStatusResponse> execute() {
        return Arrays.stream(CategoryStatus.values())
                .map(CategoryStatusResponse::new)
                .toList();
    }
}
