package com.example.lbs.dto.place;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UpdatePlaceRequest {

    @Size(max = 120)
    private String name;

    @Size(max = 255)
    private String address;

    private Double latitude;
    private Double longitude;

    @Size(max = 500)
    private String explanation;
}
