package com.example.lbs.service;

import com.example.lbs.common.ApiException;
import com.example.lbs.domain.Place;
import com.example.lbs.dto.place.PlaceDetailDto;
import com.example.lbs.dto.place.UpdatePlaceRequest;
import com.example.lbs.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository repo;

    @Transactional(readOnly = true)
    public PlaceDetailDto findOne(Long id) {
        Place p = repo.findById(id).orElseThrow(() -> new ApiException(404, "장소가 없습니다."));
        return toDto(p);
    }

    public PlaceDetailDto update(Long id, UpdatePlaceRequest req) {
        Place p = repo.findById(id).orElseThrow(() -> new ApiException(404, "장소가 없습니다."));

        if (req.getName() != null)        p.setName(req.getName());
        if (req.getAddress() != null)     p.setAddress(req.getAddress());
        if (req.getLatitude() != null)    p.setLatitude(req.getLatitude());
        if (req.getLongitude() != null)   p.setLongitude(req.getLongitude());
        if (req.getExplanation() != null) p.setExplanation(req.getExplanation());

        // JPA 변경감지로 UPDATE 반영
        return toDto(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ApiException(404, "장소가 없습니다.");
        repo.deleteById(id);
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
