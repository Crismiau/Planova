package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.VenueEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueEntityMapper {
    Venue toDomain(VenueEntity e);
    VenueEntity toEntity(Venue v);
    List<Venue> toDomainList(List<VenueEntity> list);
}
