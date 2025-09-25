package com.example.lbs.controller;

import com.example.lbs.dto.kakao.KakaoPlaceDto;
import com.example.lbs.dto.place.PlaceDetailDto;
import com.example.lbs.dto.place.UpdatePlaceRequest;
import com.example.lbs.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lbs.service.KakaoApiService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final KakaoApiService kakaoApiService;

    // GET /api/places/{placeId}
    @GetMapping("/{placeId}")
    public PlaceDetailDto getOne(@PathVariable Long placeId) {
        return placeService.findOne(placeId);
    }

    // PUT /api/places/{placeId}
    @PutMapping("/{placeId}")
    public PlaceDetailDto update(@PathVariable Long placeId,
                                 @Valid @RequestBody UpdatePlaceRequest req) {
        return placeService.update(placeId, req);
    }

    // DELETE /api/places/{placeId}
    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> delete(@PathVariable Long placeId) {
        placeService.delete(placeId);
        return ResponseEntity.noContent().build();
    }


    // GET /places/search?query=검색어
    @GetMapping("/search")
    public List<KakaoPlaceDto> searchPlaces(@RequestParam("query") String query) {
        return kakaoApiService.searchPlacesByKeyword(query).getDocuments();
    }
}
