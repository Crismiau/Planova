package com.Planova.PlanovaCode.domain.ports.out;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.models.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    List<Venue> findAll();
    Page<Venue> findAll(Pageable pageable);
    Optional<Venue> findById(Long id);
    Optional<Venue> findByName(String name);
    void deleteById(Long id);



}
