// src/main/java/com/Planova/PlanovaCode/services/EventService.java
package com.Planova.PlanovaCode.services;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.EventUpdateDTO;
import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// First HU1
@Service
public class EventService {
    private final EventRepository repo;
    private final VenueService venueService;

    public EventService(EventRepository repo, VenueService venueService) {
        this.repo = repo;
        this.venueService = venueService;
    }
    public EventDTO create(EventCreationDTO creationDTO) {

        Optional<EventDTO> existingEvent = repo.findByName(creationDTO.getName());
        if (existingEvent.isPresent()) {
            throw new IllegalArgumentException(
                    "An event with the name '" + creationDTO.getName() + "' already exists."
            );
        }


        VenueDTO v = venueService.findByName(creationDTO.getVenueName());
        if (v == null) {
            throw new IllegalArgumentException("Venue not found with name: " + creationDTO.getVenueName());
        }

        // Crear evento
        EventDTO e = new EventDTO();
        e.setName(creationDTO.getName());
        e.setDescription(creationDTO.getDescription());
        e.setCapacity(creationDTO.getCapacity());
        e.setVenueName(creationDTO.getVenueName());

        return repo.save(e);
    }


    public List<EventDTO> findAll() {
        return repo.findAll();
    }

    public Optional<EventDTO> findById(long id) {
        return repo.findById(id);
    }

    public EventDTO update(long id, EventUpdateDTO dto) {
        var existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        VenueDTO v = venueService.findByName(dto.getVenueName());
        if (v == null) throw new IllegalArgumentException("Venue not found with name: " + dto.getVenueName());

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setCapacity(dto.getCapacity());
        existing.setVenueName(dto.getVenueName());

        return repo.save(existing);
    }

    public boolean delete(long id) {
        return repo.deleteById(id);
    }
}
