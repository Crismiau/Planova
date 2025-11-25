package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.domain.events.ports.in.CreateEventUseCase;
import com.Planova.PlanovaCode.domain.events.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.domain.venues.ports.out.VenueRepositoryPort;
import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import com.Planova.PlanovaCode.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    @Override
    public Event create(Event event) {

        eventRepository.findByName(event.getName()).ifPresent(e -> {
            throw new DuplicateResourceException("Event name already exists");
        });

        venueRepository.findById(event.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue does not exist"));

        return eventRepository.save(event);
    }
}
