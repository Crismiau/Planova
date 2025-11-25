package com.Planova.PlanovaCode.domain.ports.out;

import com.Planova.PlanovaCode.domain.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);
    List<Event> findAll();
    Page<Event> findAll(Pageable pageable); // Add this for pagination
    Optional<Event> findById(Long id);
    Optional<Event> findByName(String name);
    void deleteById(Long id);
}
