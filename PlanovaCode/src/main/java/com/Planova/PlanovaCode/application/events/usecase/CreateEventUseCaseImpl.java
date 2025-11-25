package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.CreateEventUseCase;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
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

        // Validación nombre duplicado
        eventRepository.findByName(event.getName()).ifPresent(e -> {
            throw new DuplicateResourceException("Event name already exists");
        });

        // Validación venue EXISTE (por nombre)
        venueRepository.findByName(event.getVenueName())
                .orElseThrow(() -> new ResourceNotFoundException("Venue does not exist"));

        // Guardar evento
        return eventRepository.save(event);
    }
}

