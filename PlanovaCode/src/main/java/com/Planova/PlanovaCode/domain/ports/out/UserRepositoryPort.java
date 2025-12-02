package com.Planova.PlanovaCode.domain.ports.out;

import com.Planova.PlanovaCode.domain.models.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
}
