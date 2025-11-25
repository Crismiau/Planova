package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Venue;

public interface UpdateVenueUseCase {
    Venue update(Long id, Venue venue);
}
