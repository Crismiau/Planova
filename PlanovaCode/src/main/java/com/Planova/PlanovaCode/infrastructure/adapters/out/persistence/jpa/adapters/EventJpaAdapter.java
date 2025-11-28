package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.adapters;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.VenueEntity;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories.EventJPARepository;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories.VenueJPARepository;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.specification.EventSpecification;
import com.Planova.PlanovaCode.infrastructure.mapper.EventEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Profile("jpa")
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJPARepository repo;
    private final VenueJPARepository venueRepo; // Injected Venue Repo
    private final EventEntityMapper mapper;

    @Override
    public Event save(Event event) {
        EventEntity e = mapper.toEntity(event);

        // Resolve and set Venue
        if (event.getVenueName() != null && !event.getVenueName().isEmpty()) {
            VenueEntity venueEntity = venueRepo.findByName(event.getVenueName())
                    .orElseThrow(() -> new IllegalStateException("Venue not found: " + event.getVenueName() + ". This should have been validated earlier."));
            e.setVenue(venueEntity);
        }

        EventEntity saved = repo.save(e);
        return mapper.toDomain(saved);
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        List<EventEntity> entities = repo.findAll();
        return entities.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return repo.findByName(name).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }




    // Specifications query's and filters:

    @Override
    public List<Event> findByVenue(Long venueId){
        return mapper.toDomainList(repo.findByVenueId(venueId));
    }

    @Override
    public List<Event> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return mapper.toDomainList(repo.findByDateRange(start, end));
    }

    @Override
    public List<Event> searchFiltered(Long venueId, String category, LocalDateTime startDate, LocalDateTime endDate) {
        Specification<EventEntity> spec =
                Specification.where(EventSpecification.venueIs(venueId)).and(EventSpecification.categoryIs(category)).and(EventSpecification.startDateAfter(startDate)).and(EventSpecification.endDateBefore(endDate));
        return mapper.toDomainList(repo.findAll(spec));

    }

    @Override
    public List<Event> findAllOptimized() {
        return mapper.toDomainList(repo.findAllFetchVenue());
    }

}
