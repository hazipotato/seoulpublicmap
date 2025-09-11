package com.example.lbs.repository;

import java.util.*;
import com.example.lbs.domain.PlaceList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceListRepository extends JpaRepository<PlaceList, Long> {
    List<PlaceList> findByNameContainingIgnoreCase(String name); // ← 추가
}
