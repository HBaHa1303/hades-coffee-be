package com.hades.hadescoffee.presentation.dto.requests.tables;

import com.hades.hadescoffee.domain.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeTableStatusRequest {
    TableStatus status;
}