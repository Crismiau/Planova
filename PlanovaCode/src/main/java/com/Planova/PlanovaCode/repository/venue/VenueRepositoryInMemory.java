// src/main/java/com/Planova/PlanovaCode/repository/VenueRepositoryJPA.java
package com.Planova.PlanovaCode.repository.venue;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.entity.VenueEntity;
import com.Planova.PlanovaCode.repository.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
// First HU1
@Repository
public class VenueRepositoryInMemory implements IGenericRepository<VenueEntity, Long> {

    private final List<VenueEntity> store = new ArrayList<>();
    private long nextId = 1L;


    //    ------------------- Métodos heredados de la interfaz



    @Override
    public synchronized VenueEntity save(VenueEntity venue) {
        if (venue.getId() == 0) {
            venue.setId(nextId++);
            store.add(venue);
        } else {
            deleteById(venue.getId());
            store.add(venue);
        }
        return venue;
    }

    @Override
    public synchronized List<VenueEntity> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public synchronized Optional<VenueEntity> findById(Long id) {
        return store.stream().filter(v -> Objects.equals(v.getId(), id)).findFirst();
    }

    @Override
    public synchronized void deleteById(Long id) {
         store.removeIf(v -> Objects.equals(v.getId(), id));
    }




    public synchronized Optional<VenueEntity> findByName(String name) {
        return store.stream().filter(v -> v.getName().equalsIgnoreCase(name)).findFirst();
    }
    public synchronized void clearAll() {
        store.clear();
        nextId = 1;
    }
}
