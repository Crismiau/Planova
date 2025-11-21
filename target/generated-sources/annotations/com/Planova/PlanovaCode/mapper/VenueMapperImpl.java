package com.Planova.PlanovaCode.mapper;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.entity.VenueEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-20T11:41:52-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class VenueMapperImpl implements VenueMapper {

    @Override
    public VenueDTO toDTO(VenueEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VenueDTO venueDTO = new VenueDTO();

        venueDTO.setId( entity.getId() );
        venueDTO.setName( entity.getName() );
        venueDTO.setDirection( entity.getDirection() );

        return venueDTO;
    }

    @Override
    public VenueEntity toEntity(VenueDTO dto) {
        if ( dto == null ) {
            return null;
        }

        VenueEntity.VenueEntityBuilder venueEntity = VenueEntity.builder();

        venueEntity.id( dto.getId() );
        venueEntity.name( dto.getName() );
        venueEntity.direction( dto.getDirection() );

        return venueEntity.build();
    }

    @Override
    public List<VenueDTO> toDTOList(List<VenueEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<VenueDTO> list = new ArrayList<VenueDTO>( entities.size() );
        for ( VenueEntity venueEntity : entities ) {
            list.add( toDTO( venueEntity ) );
        }

        return list;
    }
}
