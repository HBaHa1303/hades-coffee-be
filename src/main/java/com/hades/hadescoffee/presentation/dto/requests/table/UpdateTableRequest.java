package com.hades.hadescoffee.presentation.dto.requests.table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTableRequest {
    String name;
}
