package com.hades.hadescoffee.presentation.dto.response;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    Long id;
    String name;
    CategoryStatus status;
}
