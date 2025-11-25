package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventEntityMapper {

    @Mapping(source = "venue.id", target = "venueId")
    Event toDomain(EventEntity entity);

    @Mapping(source = "venueId", target = "venue.id")
    EventEntity toEntity(Event domain);

    List<Event> toDomainList(List<EventEntity> entities);
}
