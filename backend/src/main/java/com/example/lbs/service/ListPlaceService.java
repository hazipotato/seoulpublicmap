package com.example.lbs.service;

import com.example.lbs.common.ApiException;
import com.example.lbs.domain.ListPlace;
import com.example.lbs.domain.Place;
import com.example.lbs.domain.PlaceList;
import com.example.lbs.dto.list.AddOrCreatePlaceRequest;
import com.example.lbs.dto.place.PlaceDetailDto;
import com.example.lbs.repository.ListPlaceRepository;
import com.example.lbs.repository.PlaceListRepository;
import com.example.lbs.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ListPlaceService {

    private final PlaceListRepository placeListRepository;
    private final PlaceRepository placeRepository;
    private final ListPlaceRepository listPlaceRepository;

    /** 리스트의 장소 목록 조회 */
    @Transactional(readOnly = true)
    public List<PlaceDetailDto> getPlaces(Long listId) {
        if (!placeListRepository.existsById(listId)) {
            throw new ApiException(404, "리스트가 없습니다.");
        }
        return listPlaceRepository.findByList_Id(listId).stream()
                .map(lp -> toDto(lp.getPlace()))
                .toList();
    }

    /**
     * 리스트에 장소 추가
     * - req.placeId 가 있으면 기존 장소를 연결
     * - 없으면 name/latitude/longitude 로 새 Place 생성 후 연결 (address/explanation 옵션)
     * - 이미 연결되어 있으면 중복 저장 없이 그대로 반환
     */
    public PlaceDetailDto addOrCreatePlace(Long listId, AddOrCreatePlaceRequest req) {
        PlaceList list = placeListRepository.findById(listId)
                .orElseThrow(() -> new ApiException(404, "리스트가 없습니다."));

        Place place;
        if (req.getPlaceId() != null) {
            place = placeRepository.findById(req.getPlaceId())
                    .orElseThrow(() -> new ApiException(404, "장소가 없습니다."));
        } else {
            if (req.getName() == null || req.getName().isBlank()
                    || req.getLatitude() == null || req.getLongitude() == null) {
                throw new ApiException(400, "name/latitude/longitude는 필수입니다.");
            }
            place = placeRepository.save(Place.builder()
                    .name(req.getName())
                    .address(req.getAddress())
                    .latitude(req.getLatitude())
                    .longitude(req.getLongitude())
                    .explanation(req.getExplanation())
                    .build());
        }

        if (!listPlaceRepository.existsByList_IdAndPlace_Id(listId, place.getId())) {
            listPlaceRepository.save(ListPlace.builder()
                    .list(list)
                    .place(place)
                    .build());
        }

        return toDto(place);
    }

    /** 리스트에서 장소 제거 */
    public void removePlace(Long listId, Long placeId) {
        if (!placeListRepository.existsById(listId)) {
            throw new ApiException(404, "리스트가 없습니다.");
        }
        if (!placeRepository.existsById(placeId)) {
            throw new ApiException(404, "장소가 없습니다.");
        }
        listPlaceRepository.deleteByList_IdAndPlace_Id(listId, placeId);
    }

    // === 내부 변환 ===
    private static PlaceDetailDto toDto(Place p) {
        return new PlaceDetailDto(
                p.getId(),
                p.getName(),
                p.getAddress(),
                p.getLatitude(),
                p.getLongitude(),
                p.getExplanation()
        );
    }
}
