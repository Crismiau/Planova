package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllEventsUseCase {
    Page<Event> getAll(Pageable pageable);
}
