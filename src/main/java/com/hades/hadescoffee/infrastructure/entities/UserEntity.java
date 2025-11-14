package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.RoleStatusEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
    @Id
    Long id;

    @Column(nullable = false, length = 50)
    String email;

    @Column(nullable = false, length = 60)
    String password;

    @Column(nullable = false, length = 50)
    String firstName;

    @Column(nullable = false, length = 50)
    String lastName;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    RoleStatusEntity status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserRoleEntity> userRoles = List.of();
}
