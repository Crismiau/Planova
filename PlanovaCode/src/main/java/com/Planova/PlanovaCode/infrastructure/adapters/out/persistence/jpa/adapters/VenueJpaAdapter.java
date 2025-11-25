package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.adapters;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories.VenueJPARepository;
import com.Planova.PlanovaCode.infrastructure.mapper.VenueEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueJPARepository repo;
    private final VenueEntityMapper mapper;

    @Override
    public Venue save(Venue venue) {
        var entity = mapper.toEntity(venue);
        var saved = repo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Page<Venue> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Venue> findByName(String name) {
        return repo.findByName(name).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}