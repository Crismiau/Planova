package com.Planova.PlanovaCode.mapper;


import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.entity.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);


    VenueDTO toDTO(VenueEntity entity);
    VenueEntity toEntity(VenueDTO dto);
    List<VenueDTO> toDTOList(List<VenueEntity> entities);


}
