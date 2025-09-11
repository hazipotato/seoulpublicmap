package com.example.lbs.controller;

import com.example.lbs.dto.place.PlaceDetailDto;
import com.example.lbs.dto.place.UpdatePlaceRequest;
import com.example.lbs.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

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
}
