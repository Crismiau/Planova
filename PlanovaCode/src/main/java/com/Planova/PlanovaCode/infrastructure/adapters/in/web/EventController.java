package com.Planova.PlanovaCode.infrastructure.adapters.in.web;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.*;
import com.Planova.PlanovaCode.infrastructure.mapper.EventDtoMapper;
import com.Planova.PlanovaCode.shared.dto.EventRequestDTO;
import com.Planova.PlanovaCode.shared.dto.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createUseCase;
    private final GetAllEventsUseCase getAllUseCase;
    private final GetEventByIdUseCase getByIdUseCase;
    private final UpdateEventUseCase updateUseCase;
    private final DeleteEventUseCase deleteUseCase;
    private final EventDtoMapper dtoMapper;


    @Operation(summary = "Create event")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Venue not found"),
            @ApiResponse(responseCode = "409", description = "Event name already exists")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDTO create(@RequestBody EventRequestDTO req) {
        Event domain = dtoMapper.toDomain(req);
        Event created = createUseCase.create(domain);
        return dtoMapper.toResponse(created);
    }

    @Operation(summary = "Get all events")
    @ApiResponse(responseCode = "200", description = "List of events returned successfully")
    @GetMapping
    public Page<EventResponseDTO> getAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Event> eventPage = getAllUseCase.getAll(pageable);
        return eventPage.map(dtoMapper::toResponse);
    }

    @Operation(summary = "Get event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}")
    public EventResponseDTO getById(@PathVariable Long id) {
        Event event = getByIdUseCase.getById(id);
        return dtoMapper.toResponse(event);
    }

    @Operation(summary = "Update event")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "409", description = "Duplicated event name")
    })
    @PutMapping("/{id}")
    public EventResponseDTO update(@PathVariable Long id, @RequestBody EventRequestDTO req) {
        Event domain = dtoMapper.toDomain(req);
        Event updated = updateUseCase.update(id, domain);
        return dtoMapper.toResponse(updated);
    }

    @Operation(summary = "Delete event")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Event deleted"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = deleteUseCase.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
