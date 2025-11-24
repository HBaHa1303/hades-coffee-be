package com.hades.hadescoffee.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdTime;

    @CreatedBy
    @Column(nullable = false)
    Long createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    Long lastModifiedBy;

    @LastModifiedDate
    @Column(nullable = false)
    Instant lastModifiedTime;
}
