// src/main/java/com/Planova/PlanovaCode/services/VenueServiceImpl.java
package com.Planova.PlanovaCode.services;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.repository.IVenueService;
import com.Planova.PlanovaCode.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// First HU1
@Service
public class VenueServiceImpl implements IVenueService {
    private final VenueRepository repo;

    public VenueServiceImpl(VenueRepository repo) {
        this.repo = repo;
    }

    @Override
    public VenueDTO create(VenueDTO dto) {
        dto.setId(0);
        return repo.save(dto);
    }

    @Override
    public List<VenueDTO> findAll() {
        return repo.findAll();
    }

    @Override
    public VenueDTO findById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public VenueDTO findByName(String name) {
        return repo.findByName(name).orElse(null);
    }

    @Override
    public VenueDTO update(long id, VenueDTO dto) {
        var existing = repo.findById(id).orElse(null);
        if (existing == null) return null;
        dto.setId(id);
        return repo.save(dto);
    }

    @Override
    public boolean delete(long id) {
        return repo.deleteById(id);
    }
}

//