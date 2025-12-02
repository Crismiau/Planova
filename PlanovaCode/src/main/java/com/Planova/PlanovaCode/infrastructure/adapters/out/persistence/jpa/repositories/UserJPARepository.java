package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories;

import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
