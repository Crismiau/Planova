package com.Planova.PlanovaCode.application.venue.usecase;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.in.CreateVenueUseCase;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue create(Venue venue) {
        venueRepository.findByName(venue.getName()).ifPresent(v -> {
            throw new DuplicateResourceException("Venue name already exists");
        });
        return venueRepository.save(venue);
    }
}
