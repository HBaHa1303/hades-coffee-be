package com.hades.hadescoffee.infrastructure.entities;

import com.hades.hadescoffee.infrastructure.enums.RoleStatusEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    RoleEntity role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,  length = 20)
    RoleStatusEntity status;
}

