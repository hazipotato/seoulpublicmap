package com.example.lbs.dto.place;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PlaceDetailDto {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String explanation; // nullable
}
