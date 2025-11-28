    package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity;


    import jakarta.persistence.*;
    import jdk.jfr.DataAmount;
    import lombok.*;

    import java.util.ArrayList;
    import java.util.List;


    @Entity
    @Table(name = "venues")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class VenueEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        private String direction;

        private String city;

        // RELACIÓN: 1 Venue → Muchos eventos
        @OneToMany(
                mappedBy = "venue",            // referencia al nombre del atributo en EventEntity
                cascade = CascadeType.ALL,     // propaga persist/merge/remove a eventos
                orphanRemoval = true,          // elimina eventos huérfanos
                fetch = FetchType.LAZY         // recomendado para evitar sobrecarga
        )
        private List<EventEntity> events = new ArrayList<>();
    }




