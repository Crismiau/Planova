package com.Planova.PlanovaCode.application.venue.usecase;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.in.UpdateVenueUseCase;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import com.Planova.PlanovaCode.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue update(Long id, Venue venue) {
        Venue existing = venueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue not found"));

        if (!existing.getName().equalsIgnoreCase(venue.getName())) {
            venueRepository.findByName(venue.getName()).ifPresent(v -> {
                throw new DuplicateResourceException("Another venue with this name already exists");
            });
        }

        existing.setName(venue.getName());
        existing.setDirection(venue.getDirection());
        existing.setCity(venue.getCity());

        return venueRepository.save(existing);
    }
}
