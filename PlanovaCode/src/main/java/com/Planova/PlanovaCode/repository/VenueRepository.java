// src/main/java/com/Planova/PlanovaCode/repository/VenueRepository.java
package com.Planova.PlanovaCode.repository;

import com.Planova.PlanovaCode.dto.VenueDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// First HU1
@Repository
public class VenueRepository {
    private final List<VenueDTO> store = new ArrayList<>();
    private long nextId = 1L;

    public VenueRepository() {
        // datos de prueba por defecto (dev)
        VenueDTO v1 = new VenueDTO();
        v1.setId(nextId++);
        v1.setName("Principal Stadium");
        v1.setDirection("Calle 16 #104-43");
        store.add(v1);

        VenueDTO v2 = new VenueDTO();
        v2.setId(nextId++);
        v2.setName("Riwi S.A.S");
        v2.setDirection("Calle 23 #24-32");
        store.add(v2);
    }

    public synchronized VenueDTO save(VenueDTO venue) {
        if (venue.getId() == 0) {
            venue.setId(nextId++);
            store.add(venue);
        } else {
            deleteById(venue.getId());
            store.add(venue);
        }
        return venue;
    }

    public synchronized List<VenueDTO> findAll() {
        return new ArrayList<>(store);
    }

    public synchronized Optional<VenueDTO> findById(long id) {
        return store.stream().filter(v -> v.getId() == id).findFirst();
    }

    public synchronized Optional<VenueDTO> findByName(String name) {
        return store.stream().filter(v -> v.getName().equalsIgnoreCase(name)).findFirst();
    }

    public synchronized boolean deleteById(long id) {
        return store.removeIf(v -> v.getId() == id);
    }

    public synchronized void clearAll() {
        store.clear();
        nextId = 1;
    }
}
