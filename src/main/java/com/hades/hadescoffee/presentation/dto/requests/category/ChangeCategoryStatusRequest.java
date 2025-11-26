package com.hades.hadescoffee.presentation.dto.requests.category;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeCategoryStatusRequest {
    CategoryStatus status;
}
