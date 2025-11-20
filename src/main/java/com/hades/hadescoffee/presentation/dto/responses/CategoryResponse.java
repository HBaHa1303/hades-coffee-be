package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    Long id;
    String name;
    CategoryStatus status;
}
