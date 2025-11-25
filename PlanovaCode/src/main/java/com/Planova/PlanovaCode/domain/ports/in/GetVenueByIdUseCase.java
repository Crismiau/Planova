package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Venue;

public interface GetVenueByIdUseCase {
    Venue getById(Long id);
}
