package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories;


import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface EventJPARepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    Optional<EventEntity> findByName(String name);

    // Search events for venue
    @Query("SELECT e FROM EventEntity e WHERE e.venue.id = :venueId")
    List<EventEntity> findByVenueId(@Param("venueId") Long venueId);


    @Query("SELECT e FROM EventEntity e JOIN FETCH e.venue")
    List<EventEntity> findAllFetchVenue();


    @Query("SELECT e From EventEntity e WHERE e.startDate BETWEEN :start and :end")
    List<EventEntity> findByDateRange(LocalDateTime start, LocalDateTime end);

    @EntityGraph(attributePaths = {"venue"})
    @Query("SELECT e FROM EventEntity e")
    List<EventEntity> findAllWithVenue();

}
