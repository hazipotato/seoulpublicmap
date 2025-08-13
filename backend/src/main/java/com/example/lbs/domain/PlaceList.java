package com.example.lbs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "place_list")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaceList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}