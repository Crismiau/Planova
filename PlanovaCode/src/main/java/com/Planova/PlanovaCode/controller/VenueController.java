// src/main/java/com/Planova/PlanovaCode/controller/VenueController.java
package com.Planova.PlanovaCode.controller;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.services.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
// First HU1
@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService service;

    public VenueController(VenueService service) {
        this.service = service;
    }

    // ---------------- CREATE ----------------
    @Operation(summary = "Create venue")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Venue created successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "id": 3,
                                      "name": "New Venue",
                                      "direction": "Street 45 #12-89"
                                    }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueDTO dto) {
        VenueDTO created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }



    // ---------------- GET ALL ----------------
    @Operation(summary = "Get all venues")
    @ApiResponse(responseCode = "200", description = "List of venues returned successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject("""
                            [
                              {
                                "id": 1,
                                "name": "Principal Stadium",
                                "direction": "Calle 16 #104-43"
                              },
                              {
                                "id": 2,
                                "name": "Riwi S.A.S",
                                "direction": "Calle 23 #24-32"
                              }
                            ]
                            """)
            ))
    @GetMapping
    public ResponseEntity<List<VenueDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }



    // ---------------- GET BY ID ----------------
    @Operation(summary = "Get venue by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject("""
                                    {
                                      "id": 1,
                                      "name": "Principal Stadium",
                                      "direction": "Calle 16 #104-43"
                                    }
                                    """)
                    )),
            @ApiResponse(responseCode = "404", description = "Venue not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject("""
                                    { "error": "Venue not found" }
                                    """)
                    ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getById(@PathVariable long id) {
        var v = service.findById(id);
        if (v == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(v);
    }







    // ---------------- UPDATE ----------------
    @Operation(summary = "Update venue")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue updated successfully",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject("""
                                    {
                                      "id": 1,
                                      "name": "Updated Venue",
                                      "direction": "New Street 10 #20-30"
                                    }
                                    """)
                    )),
            @ApiResponse(responseCode = "404", description = "Venue not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject("""
                                    { "error": "Venue with ID 99 not found" }
                                    """)
                    ))
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(@PathVariable long id, @Valid @RequestBody VenueDTO dto) {
        var updated = service.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }





    // ---------------- DELETE ----------------
    @Operation(summary = "Delete venue")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Venue deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Venue not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject("""
                                    { "error": "Venue with ID 50 not found" }
                                    """)
                    ))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
