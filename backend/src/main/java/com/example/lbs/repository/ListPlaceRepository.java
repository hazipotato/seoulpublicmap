package com.example.lbs.repository;

import com.example.lbs.domain.ListPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListPlaceRepository extends JpaRepository<ListPlace, Long> {
    List<ListPlace> findByList_Id(Long listId);
    boolean existsByList_IdAndPlace_Id(Long listId, Long placeId);
    void deleteByList_IdAndPlace_Id(Long listId, Long placeId);
}
