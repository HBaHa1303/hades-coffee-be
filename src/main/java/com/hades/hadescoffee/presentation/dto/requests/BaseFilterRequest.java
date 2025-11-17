package com.hades.hadescoffee.presentation.dto.requests;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseFilterRequest {
    int page = 0;
    int size = 10;
    private String sortBy = "createdTime";
    private boolean asc = false;
    private String keyword;
}

