package com.Planova.PlanovaCode.services.interfaces;

import com.Planova.PlanovaCode.dto.VenueDTO;

import java.util.List;

public interface IVenueService {

    VenueDTO create(VenueDTO dto);

    List<VenueDTO> findAll();

    VenueDTO findById(long id);

    VenueDTO update(long id, VenueDTO dto);

    boolean delete(long id);
}
