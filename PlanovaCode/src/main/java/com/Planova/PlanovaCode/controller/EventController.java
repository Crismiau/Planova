package com.Planova.PlanovaCode.controller;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.EventUpdateDTO;
import com.Planova.PlanovaCode.services.interfaces.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

// First HU1
@RestController
@RequestMapping("/events")
public class EventController {

    private final IEventService service;

    public EventController(IEventService service) {
        this.service = service;
    }





    //    -----------------------------------------------------------------------------
                                        // Create Event


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
    public EventDTO createEvent(@Valid @RequestBody EventCreationDTO dto) {
 return service.create(dto);
        }



    //    -----------------------------------------------------------------------------
                                        // Get All

    @Operation(
            summary = "Get all events",
            description = "Returns a list of all events stored in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of events returned successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<EventDTO>> getAll(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio
    ) {
        Page<EventDTO> events = service.findAll(pageable, city, category, fechaInicio);

        return ResponseEntity.ok(events);
    }




    //    -----------------------------------------------------------------------------
                                           // Get By Id



    @Operation(summary = "Get event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    { "error": "Event with id 99 not found" }
                                    """)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }







    //    -----------------------------------------------------------------------------
                                            // Update By Id



    @Operation(summary = "Update event")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "409", description = "Duplicated event name",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    { "error": "Event name already exists" }
                                    """)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable long id, @Valid @RequestBody EventUpdateDTO dto) {
        EventDTO updated = service.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }








    //    -----------------------------------------------------------------------------
                                            // Delete by Id



    @Operation(summary = "Delete event")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Event deleted"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
