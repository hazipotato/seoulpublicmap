package com.example.lbs.domain;
//여기가 place도메인
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Place {

    @Column(unique = true)
    private Long kakaoPlaceId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 255)
    private String address;


    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 500)
    private String explanation; // 옵션 메모
}
