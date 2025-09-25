package com.example.lbs.dto.list;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class AddOrCreatePlaceRequest {
    @NotNull
    private Long kakaoPlaceId;

    @NotBlank
    private String placeName;

    @NotBlank
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}