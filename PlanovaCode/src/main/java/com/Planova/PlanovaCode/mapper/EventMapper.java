package com.Planova.PlanovaCode.mapper;


import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "venue.name", target = "venueName")
    EventDTO toDTO(EventEntity entity);
    EventEntity toEntity(EventDTO dto);
    List<EventDTO> toDTOList(List<EventEntity> entities);

}
