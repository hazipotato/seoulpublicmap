package com.example.lbs.domain;
//여기가 걍 list도메인임
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