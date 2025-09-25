package com.example.lbs.repository;

import com.example.lbs.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByKakaoPlaceId(Long kakaoPlaceId);
}