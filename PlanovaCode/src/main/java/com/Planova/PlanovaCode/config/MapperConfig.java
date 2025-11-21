package com.Planova.PlanovaCode.config;

import com.Planova.PlanovaCode.mapper.EventMapper;
import com.Planova.PlanovaCode.mapper.VenueMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public EventMapper eventMapper(){
            return Mappers.getMapper(EventMapper.class);
    }

    @Bean
    public VenueMapper venueMapper(){
        return Mappers.getMapper(VenueMapper.class);
    }

}
