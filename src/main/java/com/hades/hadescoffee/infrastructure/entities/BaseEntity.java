package com.hades.hadescoffee.infrastructure.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @CreatedDate
    Instant createdTime;

    @CreatedBy
    Long createdBy;

    @LastModifiedBy
    Long lastModifiedBy;

    @LastModifiedDate
    Instant lastModifiedTime;
}
