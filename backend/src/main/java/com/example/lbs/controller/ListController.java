package com.example.lbs.controller;

import com.example.lbs.dto.list.CreateListRequest;
import com.example.lbs.dto.list.ListSummaryDto;
import com.example.lbs.dto.list.UpdateListRequest;
import com.example.lbs.dto.list.AddOrCreatePlaceRequest;
import com.example.lbs.dto.place.PlaceDetailDto;
import com.example.lbs.service.ListService;
import com.example.lbs.service.ListPlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListController {

    private final ListService listService;
    private final ListPlaceService listPlaceService;
    // 모든 리스트 조회 GET /api/lists -> [{ id, name }]
    @GetMapping
    public List<ListSummaryDto> getAll() {
        return listService.findAll();
    }

    // 새 리스트 생성 POST /api/lists -> { id, name }
    @PostMapping
    public ResponseEntity<ListSummaryDto> create(@Valid @RequestBody CreateListRequest req) {
        ListSummaryDto body = listService.create(req);
        return ResponseEntity.created(URI.create("/api/lists/" + body.getId())).body(body);
    }

    // 단일 리스트 조회 GET /api/lists/{listId} -> { id, name }
    @GetMapping("/{listId}")
    public ListSummaryDto getOne(@PathVariable Long listId) {
        return listService.findOne(listId);
    }

    // 리스트 이름 수정 PUT /api/lists/{listId} -> { id, name }
    @PutMapping("/{listId}")
    public ListSummaryDto update(@PathVariable Long listId,
                                 @Valid @RequestBody UpdateListRequest req) {
        return listService.update(listId, req);
    }

    // 리스트 삭제 DELETE /api/lists/{listId} -> 204
    @DeleteMapping("/{listId}")
    public ResponseEntity<Void> delete(@PathVariable Long listId) {
        listService.delete(listId);
        return ResponseEntity.noContent().build();
    }

    // GET /api/lists/{listId}/places
    @GetMapping("/{listId}/places")
    public List<PlaceDetailDto> getPlaces(@PathVariable Long listId) {
        return listPlaceService.getPlaces(listId);
    }

    // POST /api/lists/{listId}/places
    @PostMapping("/{listId}/places")
    public ResponseEntity<PlaceDetailDto> addPlace(@PathVariable Long listId,
                                                   @Valid @RequestBody AddOrCreatePlaceRequest req) {
        PlaceDetailDto body = listPlaceService.addOrCreatePlace(listId, req);
        return ResponseEntity.created(URI.create("/api/lists/" + listId + "/places")).body(body);
    }

    // DELETE /api/lists/{listId}/places/{placeId}
    @DeleteMapping("/{listId}/places/{placeId}")
    public ResponseEntity<Void> remove(@PathVariable Long listId, @PathVariable Long placeId) {
        listPlaceService.removePlace(listId, placeId);
        return ResponseEntity.noContent().build();
    }
}