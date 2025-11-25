package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.shared.dto.EventRequestDTO;
import com.Planova.PlanovaCode.shared.dto.EventResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {
    Event toDomain(EventRequestDTO dto);
    EventResponseDTO toResponse(Event domain);
    List<EventResponseDTO> toResponseList(List<Event> list);
}
