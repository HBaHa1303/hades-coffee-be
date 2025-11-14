package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.TableStatusEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "tables")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableEntity extends BaseEntity {
    @Id
    String code;

    @Column(nullable = false, length = 50, unique = true)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    TableStatusEnum status;
}
