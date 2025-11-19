// src/main/java/com/Planova/PlanovaCode/services/IVenueService.java
package com.Planova.PlanovaCode.repository;

import com.Planova.PlanovaCode.dto.VenueDTO;

import java.util.List;

public interface IVenueService {
    VenueDTO create(VenueDTO dto);
    List<VenueDTO> findAll();
    VenueDTO findById(long id);
    VenueDTO findByName(String name);
    VenueDTO update(long id, VenueDTO dto);
    boolean delete(long id);
}
