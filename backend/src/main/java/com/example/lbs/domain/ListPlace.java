package com.example.lbs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "list_place",
        uniqueConstraints = @UniqueConstraint(columnNames = {"list_id", "place_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ListPlace {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "list_id", nullable = false)
    private PlaceList list;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "place_id", nullable = false)
    private Place place;
}
