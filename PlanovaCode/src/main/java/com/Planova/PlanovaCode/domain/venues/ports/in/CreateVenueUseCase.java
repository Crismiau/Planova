package com.Planova.PlanovaCode.domain.venues.ports.in;
import com.Planova.PlanovaCode.domain.venues.models.Venue;

public interface CreateVenueUseCase {
    Venue create(Venue venue);
}
