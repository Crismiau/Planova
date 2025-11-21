package com.Planova.PlanovaCode.services.interfaces;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.EventUpdateDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {

        EventDTO create(EventCreationDTO dto);

        List<EventDTO> findAll();

        Page<EventDTO> findAll(Pageable pageable, String city, String category, LocalDate fechaInicio);

        Optional<EventDTO> findById(long id);

        EventDTO update(long id, EventUpdateDTO dto);

        boolean delete(long id);


}
