package com.Planova.PlanovaCode.domain.venues.ports.out;

import com.Planova.PlanovaCode.domain.venues.models.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    List<Venue> findAll();
    Optional<Venue> findById(Long id);
    Optional<Venue> findByName(String name);
    void deleteById(Long id);
}
