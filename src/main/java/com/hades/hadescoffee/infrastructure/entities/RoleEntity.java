package com.hades.hadescoffee.infrastructure.entities;

import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("roles")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity {

    @Id
    String code;

    String name;

    @Override
    public Long getId() {
        return 0L;
    }
}
