package com.example.lbs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String explanation; // 선택값
}