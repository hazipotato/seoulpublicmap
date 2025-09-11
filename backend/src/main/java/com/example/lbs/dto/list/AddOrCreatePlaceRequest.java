package com.example.lbs.dto.list;

import lombok.Data;

/*
 * 두 가지 모드:
 * 1) placeId가 있다 -> 연결만 수행
 * 2) placeId가 없다 -> name/latitude/longitude로 새 Place 생성 후 연결
 *    - explanation은 옵션으로 달아놧어
 */
@Data
public class AddOrCreatePlaceRequest {
    private Long placeId;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String explanation; // 옵션
}
