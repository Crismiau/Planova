// src/main/java/com/Planova/PlanovaCode/services/IEventService.java
package com.Planova.PlanovaCode.repository;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.EventUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    EventDTO create(EventCreationDTO creationDTO);
    List<EventDTO> findAll();
    Optional<EventDTO> findById(long id);
    EventDTO update(long id, EventUpdateDTO dto);
    boolean delete(long id);
}
