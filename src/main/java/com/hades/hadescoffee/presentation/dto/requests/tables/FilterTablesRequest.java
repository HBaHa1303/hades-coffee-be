package com.hades.hadescoffee.presentation.dto.requests.tables;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.presentation.dto.requests.BaseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterTablesRequest extends BaseFilterRequest {
    TableStatus status;
}
