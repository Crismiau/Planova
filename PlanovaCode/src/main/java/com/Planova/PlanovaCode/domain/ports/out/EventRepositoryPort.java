package com.Planova.PlanovaCode.domain.ports.out;

import com.Planova.PlanovaCode.domain.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);

    List<Event> findAll();

    Page<Event> findAll(Pageable pageable); // Add this for pagination

    Optional<Event> findById(Long id);

    Optional<Event> findByName(String name);

    void deleteById(Long id);



    // Querys for JPQL and Specificacions

    List<Event> findByVenue(Long venueId);
    List<Event> findByDateRange(LocalDateTime start, LocalDateTime end);
    List<Event> searchFiltered(
            Long venueId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
    List<Event> findAllOptimized(); // evitar N+1



}
