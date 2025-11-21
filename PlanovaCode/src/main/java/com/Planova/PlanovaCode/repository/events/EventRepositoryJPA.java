package com.Planova.PlanovaCode.repository.events;


import com.Planova.PlanovaCode.entity.EventEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface EventRepositoryJPA extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    Optional<EventEntity> findByName(String name);
}
