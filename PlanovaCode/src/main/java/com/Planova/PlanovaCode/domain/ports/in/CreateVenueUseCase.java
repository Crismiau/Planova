package com.Planova.PlanovaCode.domain.ports.in;
import com.Planova.PlanovaCode.domain.models.Venue;

public interface CreateVenueUseCase {
    Venue create(Venue venue);
}
