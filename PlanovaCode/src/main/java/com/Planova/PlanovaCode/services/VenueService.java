// src/main/java/com/Planova/PlanovaCode/services/VenueService.java
package com.Planova.PlanovaCode.services;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository repo;

    public VenueService(VenueRepository repo) {
        this.repo = repo;
    }

    public VenueDTO create(VenueDTO dto) {
        dto.setId(0); // asegurar que se genere nuevo id
        return repo.save(dto);
    }

    public List<VenueDTO> findAll() {
        return repo.findAll();
    }

    public VenueDTO findById(long id) {
        return repo.findById(id).orElse(null);
    }

    public VenueDTO findByName(String name) {
        return repo.findByName(name).orElse(null);
    }

    public VenueDTO update(long id, VenueDTO dto) {
        var existing = repo.findById(id).orElse(null);
        if (existing == null) return null;
        dto.setId(id);
        return repo.save(dto);
    }

    public boolean delete(long id) {
        return repo.deleteById(id);
    }
}
