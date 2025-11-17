package com.hades.hadescoffee.application.utils;

import com.hades.hadescoffee.presentation.dto.requests.BaseFilterRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    public static Pageable toPageable(BaseFilterRequest filter) {
        Sort sort = Sort.by(filter.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, filter.getSortBy());
        return PageRequest.of(filter.getPage(), filter.getSize(), sort);
    }
}
