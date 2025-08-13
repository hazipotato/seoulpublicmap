package com.example.lbs.repository;

import com.example.lbs.domain.PlaceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceListRepository extends JpaRepository<PlaceList, Long> { }