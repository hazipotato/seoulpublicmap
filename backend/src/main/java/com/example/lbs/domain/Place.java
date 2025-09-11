package com.example.lbs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 255)
    private String address;

    // 위도/경도는 null 허용 (스냅샷이 없을 수도 있음)
    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 500)
    private String explanation; // 옵션 메모
}
