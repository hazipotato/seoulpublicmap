package com.example.lbs.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoPlaceDto {

    @JsonProperty("id")
    private Long kakaoPlaceId;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("address_name")
    private String address;

    @JsonProperty("y")
    private Double latitude;

    @JsonProperty("x")
    private Double longitude;
}