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

    // 리스트의 장소 목록 조회
    @Transactional(readOnly = true)
    public List<PlaceDetailDto> getPlaces(Long listId) {
        if (!placeListRepository.existsById(listId)) {
            throw new ApiException(404, "리스트가 없습니다.");
        }
        return listPlaceRepository.findByList_Id(listId).stream()
                .map(lp -> toDto(lp.getPlace()))
                .toList();
    }

    //리스트에 장소 추가

    public PlaceDetailDto addOrCreatePlace(Long listId, AddOrCreatePlaceRequest req) {
        // 리스트 존재 여부 확인
        PlaceList list = placeListRepository.findById(listId)
                .orElseThrow(() -> new ApiException(404, "리스트가 없습니다."));

        //  kakaoPlaceId로 우리 DB에 장소가 이미 있는지 확인
        Place place = placeRepository.findByKakaoPlaceId(req.getKakaoPlaceId())
                .orElseGet(() -> {
                    // DB에 없으면 새로 생성 함
                    return placeRepository.save(Place.builder()
                            .kakaoPlaceId(req.getKakaoPlaceId()) // kakaoPlaceId 저장
                            .name(req.getPlaceName())
                            .address(req.getAddress())
                            .latitude(req.getLatitude())
                            .longitude(req.getLongitude())
                            .build());
                });

        // 리스트와 장소의연결이 이미 존재하는지 확인
        if (listPlaceRepository.existsByListAndPlace(list, place)) {
            // 이미 연결되어 있다면, 중복 저장 없이 그대로 장소 정보를 반환
            return toDto(place);
        }

        // 연결되어 있지 않다면!!! 새로 연결(ListPlace 새 거생성)
        listPlaceRepository.save(ListPlace.builder()
                .list(list)
                .place(place)
                .build());

        return toDto(place);
    }

    // 리스트에서 장소 제거
    public void removePlace(Long listId, Long placeId) {
        if (!placeListRepository.existsById(listId)) {
            throw new ApiException(404, "리스트가 없습니다.");
        }
        if (!placeRepository.existsById(placeId)) {
            throw new ApiException(404, "장소가 없습니다.");
        }
        listPlaceRepository.deleteByList_IdAndPlace_Id(listId, placeId);
    }


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
