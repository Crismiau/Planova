package com.Planova.PlanovaCode.infrastructure.adapters.in.web;

import com.Planova.PlanovaCode.application.events.usecase.CreateEventUseCaseImpl;
import com.Planova.PlanovaCode.domain.events.models.Event;
import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.infrastructure.mapper.EventDtoMapper;
import com.Planova.PlanovaCode.shared.dto.EventRequestDTO;
import com.Planova.PlanovaCode.shared.dto.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCaseImpl createUseCase;
    private final EventDtoMapper dtoMapper;





    @Operation(summary = "Create event")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "id": 1,
                                      "name": "Music Festival",
                                      "description": "Outdoor event",
                                      "capacity": 500,
                                      "venueName": "Principal Stadium"
                                    }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    { "error": "venueName is required" }
                                    """)))
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDTO create(@RequestBody EventRequestDTO req) {
        Event domain = dtoMapper.toDomain(req);
        Event created = createUseCase.create(domain);
        return dtoMapper.toResponse(created);
    }














    // Otros endpoints...
}
