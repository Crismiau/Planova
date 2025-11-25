package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Event;

public interface UpdateEventUseCase {
    Event update(Long id, Event event);
}
