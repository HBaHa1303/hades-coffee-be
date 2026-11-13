package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("categories")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "category_sequence";
    @Id
    Long id;

    String name;

    CategoryStatus status;

    List<Long> menuItemIds = new ArrayList<>();
}
