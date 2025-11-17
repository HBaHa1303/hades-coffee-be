package com.hades.hadescoffee.presentation.dto.requests.categories;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.presentation.dto.requests.BaseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterCategoriesRequest extends BaseFilterRequest {
    private CategoryStatus status;
}
