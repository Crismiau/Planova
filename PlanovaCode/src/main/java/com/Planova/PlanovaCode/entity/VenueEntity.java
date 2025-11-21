    package com.Planova.PlanovaCode.entity;


    import jakarta.persistence.*;
    import jdk.jfr.DataAmount;
    import lombok.*;

    @Entity
    @Table(name = "venues", indexes = {
            @Index(columnList = "name")
    })
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

        // puedes agregar ciudad si quieres filter por ciudad
        private String city;
    }
