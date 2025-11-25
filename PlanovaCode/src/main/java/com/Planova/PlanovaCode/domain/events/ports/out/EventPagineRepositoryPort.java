package com.Planova.PlanovaCode.domain.events.ports.out;

import com.Planova.PlanovaCode.domain.events.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventPagineRepositoryPort {

    Page<Event> findAll(Pageable pageable, String city, String category, LocalDateTime startDate);

}
