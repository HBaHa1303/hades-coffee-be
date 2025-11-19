package com.hades.hadescoffee.presentation.dto.requests.user;

import com.hades.hadescoffee.presentation.dto.requests.BaseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterUsersRequest extends BaseFilterRequest {
}
