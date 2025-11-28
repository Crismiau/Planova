package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchEventsUseCaseImpl {

    private final EventRepositoryPort port;

    public List<Event> findByVenue(Long venueId) {
        return port.findByVenue(venueId);
    }

    public List<Event> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return port.findByDateRange(start, end);
    }

    public List<Event> findFiltered(Long venueId, String category, LocalDateTime start, LocalDateTime end) {
        return port.searchFiltered(venueId, category, start, end);
    }
}
