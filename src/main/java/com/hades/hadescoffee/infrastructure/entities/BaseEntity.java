package com.hades.hadescoffee.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdTime;

    @CreatedBy
    @Column(nullable = false)
    String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    String lastModifiedBy;

    @LastModifiedDate
    @Column(nullable = false)
    Instant lastModifiedTime;
}
