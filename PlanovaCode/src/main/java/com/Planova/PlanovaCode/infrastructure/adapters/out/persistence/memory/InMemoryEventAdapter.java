package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.memory;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class InMemoryEventAdapter implements EventRepositoryPort {

    private final Map<Long, Event> store = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(nextId.getAndIncrement());
        }
        store.put(event.getId(), copy(event));
        return copy(event);
    }

    @Override
    public List<Event> findAll() {
        return store.values().stream()
                .map(this::copy)
                .collect(Collectors.toList());
    }
    
    @Override
    public Page<Event> findAll(Pageable pageable) {
        List<Event> allEvents = new ArrayList<>(store.values());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allEvents.size());

        List<Event> pageContent = Collections.emptyList();
        if (start <= end) {
            pageContent = allEvents.subList(start, end);
        }

        return new PageImpl<>(pageContent.stream().map(this::copy).collect(Collectors.toList()), pageable, allEvents.size());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(store.get(id))
                .map(this::copy);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return store.values().stream()
                .filter(e -> e.getName() != null && e.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(this::copy);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Event> findByVenue(Long venueId) {
        return List.of();
    }

    @Override
    public List<Event> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return List.of();
    }

    @Override
    public List<Event> searchFiltered(Long venueId, String category, LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }

    @Override
    public List<Event> findAllOptimized() {
        return List.of();
    }

    private Event copy(Event e) {
        Event c = new Event();
        c.setId(e.getId());
        c.setName(e.getName());
        c.setDescription(e.getDescription());
        c.setCapacity(e.getCapacity());
        c.setVenueName(e.getVenueName());
        c.setCategory(e.getCategory());
        c.setCity(e.getCity());
        c.setStartDate(e.getStartDate());
        return c;
    }
}
