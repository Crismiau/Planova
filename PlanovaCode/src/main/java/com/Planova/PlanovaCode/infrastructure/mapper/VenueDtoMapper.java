package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.shared.dto.VenueRequestDTO;
import com.Planova.PlanovaCode.shared.dto.VenueResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueDtoMapper {
    Venue toDomain(VenueRequestDTO dto);
    VenueResponseDTO toResponse(Venue domain);
    List<VenueResponseDTO> toResponseList(List<Venue> list);
}
