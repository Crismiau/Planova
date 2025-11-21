// src/main/java/com/Planova/PlanovaCode/repository/EventRepositoryJPA.java
package com.Planova.PlanovaCode.repository.events;

import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.entity.EventEntity;
import com.Planova.PlanovaCode.repository.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
// First HU1



@Repository
public class EventRepositoryInMemory implements IGenericRepository<EventEntity, Long> {
    private final List<EventEntity> store = new ArrayList<>();
    private long nextId = 1L;



    //    ------------------- Métodos heredados de la interfaz


    @Override
    public synchronized EventEntity save(EventEntity event) {
        if (event.getId() == 0 ) {
            event.setId(nextId++);
            store.add(event);
        } else {
            // replace existing
            deleteById(event.getId());
            store.add(event);
        }
        return event;
    }

    @Override
    public synchronized List<EventEntity> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public synchronized Optional<EventEntity> findById(Long id) {
        return store.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst();
    }

    @Override
    public synchronized void deleteById(Long id) {
         store.removeIf(e -> Objects.equals(e.getId(), id));
    }


//    -------------------




    public synchronized void clearAll() {
        store.clear();
        nextId = 1;
    }

    public synchronized Optional<EventEntity> findByName(String name) {
        return store.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst();
    }


}
