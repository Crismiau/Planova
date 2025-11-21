// src/main/java/com/Planova/PlanovaCode/services/EventServiceImpl.java
package com.Planova.PlanovaCode.services.impl;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.EventUpdateDTO;
import com.Planova.PlanovaCode.entity.EventEntity;
import com.Planova.PlanovaCode.entity.VenueEntity;
import com.Planova.PlanovaCode.mapper.EventMapper;
import com.Planova.PlanovaCode.repository.events.EventRepositoryJPA;
import com.Planova.PlanovaCode.repository.venue.VenueRepositoryJPA;
import com.Planova.PlanovaCode.services.interfaces.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Join;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements IEventService {

    private final EventRepositoryJPA eventRepo;
    private final VenueRepositoryJPA venueRepo;

    @Override
    public EventDTO create(EventCreationDTO dto) {

        if (eventRepo.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("An event with this name already exists");
        }
        
        VenueEntity venue = venueRepo.findByName(dto.getVenueName())
                .orElseThrow(() -> new IllegalArgumentException("Venue does not exist"));


        EventEntity entity = new EventEntity();
       entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCapacity(dto.getCapacity());
        entity.setVenue(venue);



        return EventMapper.INSTANCE.toDTO(eventRepo.save(entity));
    }

    @Override
    public List<EventDTO> findAll() {
        return EventMapper.INSTANCE.toDTOList(eventRepo.findAll());
    }

    @Override
    public Page<EventDTO> findAll(Pageable pageable, String city, String category, LocalDate fechaInicio) {
        Specification<EventEntity> spec = Specification.where(null);

        if (StringUtils.hasText(city)) {
            spec = spec.and((root, query, cb) -> {
                Join<EventEntity, VenueEntity> venueJoin = root.join("venue");
                return cb.equal(cb.lower(venueJoin.get("city")), city.toLowerCase());
            });
        }

        if (StringUtils.hasText(category)) {
            spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
        }

        if (fechaInicio != null) {
            LocalDateTime startDateTime = fechaInicio.atStartOfDay();
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("startDate"), startDateTime));
        }

        Page<EventEntity> result = eventRepo.findAll(spec, pageable);
        return result.map(EventMapper.INSTANCE::toDTO);
    }

    @Override
    public Optional<EventDTO> findById(long id) {
        return eventRepo.findById(id).map(EventMapper.INSTANCE::toDTO);
    }

    @Override
    public EventDTO update(long id, EventUpdateDTO dto) {
        EventEntity entity = eventRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Check if the name is being changed and if the new name already exists for another event
        if (!entity.getName().equalsIgnoreCase(dto.getName()) && eventRepo.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("Another event with this name already exists");
        }

        VenueEntity venue = venueRepo.findByName(dto.getVenueName())
                .orElseThrow(() -> new IllegalArgumentException("Venue does not exist"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCapacity(dto.getCapacity());
        entity.setVenue(venue);

        return EventMapper.INSTANCE.toDTO(eventRepo.save(entity));
    }


    @Override
    public boolean delete(long id) {

        if (eventRepo.findById(id).isEmpty()) {
            return false; // No existe → el controller devolverá 404
        }

        eventRepo.deleteById(id);
        return true; // Sí existe → se eliminó → el controller devolverá 204
    }

}
