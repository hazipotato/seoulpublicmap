package com.example.lbs.service;

import com.example.lbs.dto.kakao.KakaoApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoApiService {

    private final WebClient webClient;
    private final String kakaoApiKey;

    // 생성자를 통해 WebClient와 application.yml의 값을 주입받습니다.
    public KakaoApiService(WebClient.Builder webClientBuilder,
                           @Value("${kakao.api.url}") String kakaoApiUrl,
                           @Value("${kakao.api.key}") String kakaoApiKey) {
        this.webClient = webClientBuilder
                .baseUrl(kakaoApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.kakaoApiKey = "KakaoAK " + kakaoApiKey; // 헤더 형식에 맞게 "KakaoAK " 프리픽스 추가
    }

    // 키워드로 장소를 검색하는 메소드
    public KakaoApiResponseDto searchPlacesByKeyword(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/keyword.json")
                        .queryParam("query", keyword)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, kakaoApiKey)
                .retrieve() // 응답을 받아옴
                .bodyToMono(KakaoApiResponseDto.class) // 응답 본문을 KakaoApiResponseDto로 변환
                .block(); // 비동기 처리를 동기적으로 결과를 기다림
    }
}