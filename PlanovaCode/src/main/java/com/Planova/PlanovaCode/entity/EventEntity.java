package com.Planova.PlanovaCode.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})},
        indexes = {@Index(columnList = "name"), @Index(columnList = "start_date")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // UNIQUE via table constraint

    @Column(length = 2000)
    private String description;

    private Integer capacity;

    private String category;

    private String city;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    // relación con Venue (Many Events -> One Venue)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private VenueEntity venue;
}
