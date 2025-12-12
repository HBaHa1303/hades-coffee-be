package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.domain.enums.TableStatus;
import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tables")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableEntity extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "table_sequence";
    @Id
    Long id;

    String name;

    TableStatus status;
}
