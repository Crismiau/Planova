package com.Planova.PlanovaCode.services.impl;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.entity.VenueEntity;
import com.Planova.PlanovaCode.mapper.VenueMapper;
import com.Planova.PlanovaCode.repository.IGenericRepository;
import com.Planova.PlanovaCode.services.interfaces.IVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VenueServiceImpl implements IVenueService {

    private final IGenericRepository<VenueEntity, Long> venueRepo;

    @Override
    public VenueDTO create(VenueDTO dto) {
        VenueEntity entity = VenueMapper.INSTANCE.toEntity(dto);
        VenueEntity saved = venueRepo.save(entity);
        return VenueMapper.INSTANCE.toDTO(saved);
    }

    @Override
    public List<VenueDTO> findAll() {
        return VenueMapper.INSTANCE.toDTOList(venueRepo.findAll());
    }

    @Override
    public VenueDTO findById(long id) {
        return venueRepo.findById(id).map(VenueMapper.INSTANCE::toDTO).orElse(null);
    }

    @Override
    public VenueDTO update(long id, VenueDTO dto) {
        VenueEntity entity = venueRepo.findById(id).orElse(null);
        if (entity == null) return null;

        VenueEntity updated = VenueMapper.INSTANCE.toEntity(dto);
        updated.setId(id);

        return VenueMapper.INSTANCE.toDTO(venueRepo.save(updated));
    }


    @Override
    public boolean delete(long id) {

        if (venueRepo.findById(id).isEmpty()) {
            return false; // No existe → el controller devolverá 404
        }

        venueRepo.deleteById(id);
        return true; // Sí existe → se eliminó → el controller devolverá 204
    }
}
