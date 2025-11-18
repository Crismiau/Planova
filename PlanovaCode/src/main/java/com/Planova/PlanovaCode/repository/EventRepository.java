// src/main/java/com/Planova/PlanovaCode/repository/EventRepository.java
package com.Planova.PlanovaCode.repository;

import com.Planova.PlanovaCode.dto.EventDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private final List<EventDTO> store = new ArrayList<>();
    private long nextId = 1L;

    public synchronized EventDTO save(EventDTO event) {
        if (event.getId() == 0) {
            event.setId(nextId++);
            store.add(event);
        } else {
            // replace existing
            deleteById(event.getId());
            store.add(event);
        }
        return event;
    }

    public synchronized List<EventDTO> findAll() {
        return new ArrayList<>(store);
    }

    public synchronized Optional<EventDTO> findById(long id) {
        return store.stream().filter(e -> e.getId() == id).findFirst();
    }

    public synchronized boolean deleteById(long id) {
        return store.removeIf(e -> e.getId() == id);
    }

    public synchronized void clearAll() {
        store.clear();
        nextId = 1;
    }
}
