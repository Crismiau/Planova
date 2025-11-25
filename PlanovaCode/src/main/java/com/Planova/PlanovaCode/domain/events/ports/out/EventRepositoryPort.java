package com.Planova.PlanovaCode.domain.events.ports.out;

import com.Planova.PlanovaCode.domain.events.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Optional<Event> findByName(String name);
    void deleteById(Long id);
}
