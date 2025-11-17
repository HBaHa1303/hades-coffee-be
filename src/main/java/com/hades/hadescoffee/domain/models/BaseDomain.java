package com.hades.hadescoffee.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDomain {
    String createdBy;
    Instant createdTime;
    String lastModifiedBy;
    Instant lastModifiedTime;
}
