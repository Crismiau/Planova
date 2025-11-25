package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories;


import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface EventJPARepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    Optional<EventEntity> findByName(String name);
}
