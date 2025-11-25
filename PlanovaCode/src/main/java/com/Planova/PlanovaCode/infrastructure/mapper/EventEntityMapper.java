package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventEntityMapper {

    @Mapping(source = "venue.name", target = "venueName") // si VenueEntity tiene name
    Event toDomain(EventEntity entity);

    @Mapping(target = "venue", ignore = true)
    EventEntity toEntity(Event domain);

    List<Event> toDomainList(List<EventEntity> list);
}
