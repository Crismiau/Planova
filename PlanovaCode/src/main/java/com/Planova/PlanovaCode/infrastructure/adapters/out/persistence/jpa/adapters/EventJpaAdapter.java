package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.adapters;

import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.domain.events.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories.EventJPARepository;
import com.Planova.PlanovaCode.infrastructure.mapper.EventEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Profile("jpa")
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJPARepository repo;
    private final EventEntityMapper mapper;

    @Override
    public Event save(Event event) {
        EventEntity e = mapper.toEntity(event);
        EventEntity saved = repo.save(e);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Event> findAll() {
        return mapper.toDomainList(repo.findAll());
    }

    @Override
    public Page<Event> findAll(Pageable pageable, String city, String category, LocalDateTime startDate) {
        // Construir Specification en este adapter o delegar
        // Ejemplo básico: delegar a repo.findAll(spec, pageable) (implementa spec similar a tu service antiguo)
        throw new UnsupportedOperationException("Implement Specification filtering here");
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
}
