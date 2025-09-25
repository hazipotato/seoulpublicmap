package com.example.lbs.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoApiResponseDto {

    @JsonProperty("documents")
    private List<KakaoPlaceDto> documents;
}