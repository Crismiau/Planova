package com.Planova.PlanovaCode.repository.venue;

import com.Planova.PlanovaCode.entity.VenueEntity;
import com.Planova.PlanovaCode.repository.IGenericRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface VenueRepositoryJPA extends JpaRepository<VenueEntity, Long>, IGenericRepository<VenueEntity, Long> {

    Optional<VenueEntity> findByName(String name);
}
