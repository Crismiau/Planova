package com.Planova.PlanovaCode.mapper;

import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.entity.EventEntity;
import com.Planova.PlanovaCode.entity.VenueEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-20T11:41:52-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDTO toDTO(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setVenueName( entityVenueName( entity ) );
        if ( entity.getId() != null ) {
            eventDTO.setId( entity.getId() );
        }
        eventDTO.setName( entity.getName() );
        eventDTO.setDescription( entity.getDescription() );
        if ( entity.getCapacity() != null ) {
            eventDTO.setCapacity( entity.getCapacity() );
        }

        return eventDTO;
    }

    @Override
    public EventEntity toEntity(EventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventEntity.EventEntityBuilder eventEntity = EventEntity.builder();

        eventEntity.id( dto.getId() );
        eventEntity.name( dto.getName() );
        eventEntity.description( dto.getDescription() );
        eventEntity.capacity( dto.getCapacity() );

        return eventEntity.build();
    }

    @Override
    public List<EventDTO> toDTOList(List<EventEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>( entities.size() );
        for ( EventEntity eventEntity : entities ) {
            list.add( toDTO( eventEntity ) );
        }

        return list;
    }

    private String entityVenueName(EventEntity eventEntity) {
        VenueEntity venue = eventEntity.getVenue();
        if ( venue == null ) {
            return null;
        }
        return venue.getName();
    }
}
