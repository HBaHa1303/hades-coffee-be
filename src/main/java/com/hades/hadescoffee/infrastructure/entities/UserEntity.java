package com.hades.hadescoffee.infrastructure.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document("users")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Id
    Long id;

    String email;
    String password;
    String firstName;
    String lastName;

    LocalDate dateOfBirth;

    Set<String> roleCodes = new HashSet<>();
}

