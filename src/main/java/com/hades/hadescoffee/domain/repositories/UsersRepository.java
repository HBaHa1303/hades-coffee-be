package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.User;

import java.util.Optional;

public interface UsersRepository {
    Optional<User> findById(Long id);
    void save(User user);
    Optional<User> findByEmail(String email);
}
