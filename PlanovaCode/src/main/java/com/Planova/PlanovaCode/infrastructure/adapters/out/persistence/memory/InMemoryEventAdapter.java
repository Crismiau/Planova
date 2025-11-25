package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.memory;

import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.domain.events.ports.out.EventRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@Profile("memory")
public class InMemoryEventAdapter implements EventRepositoryPort {

    private final Map<Long, Event> store = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(nextId.getAndIncrement());
        }
        store.put(event.getId(), event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public org.springframework.data.domain.Page<Event> findAll(org.springframework.data.domain.Pageable pageable, String city, String category, java.time.LocalDateTime startDate) {
        // Implementación simplificada: devolver todos en page
        List<Event> list = findAll();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new org.springframework.data.domain.PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Event> findByName(String name) {
        return store.values().stream().filter(e -> e.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
