package com.Planova.PlanovaCode.domain.events.ports.in;

import com.Planova.PlanovaCode.domain.events.models.Event;

public interface CreateEventUseCase {
    Event create(Event event);
}
