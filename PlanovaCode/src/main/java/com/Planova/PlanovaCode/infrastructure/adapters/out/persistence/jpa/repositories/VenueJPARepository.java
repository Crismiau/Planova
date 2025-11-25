package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories;

import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.VenueEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface VenueJPARepository extends JpaRepository<VenueEntity, Long> {

    Optional<VenueEntity> findByName(String name);
}
