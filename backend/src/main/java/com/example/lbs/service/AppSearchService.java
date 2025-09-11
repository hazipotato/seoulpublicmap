package com.example.lbs.service;

import com.example.lbs.dto.search.CourseSearchDto;
import com.example.lbs.dto.search.ListSearchDto;
import com.example.lbs.repository.CourseRepository;
import com.example.lbs.repository.PlaceListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppSearchService {

    private final PlaceListRepository placeListRepository;
    private final CourseRepository courseRepository;

    public List<ListSearchDto> searchLists(String query) {
        if (query == null || query.isBlank()) return List.of();
        return placeListRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(l -> new ListSearchDto(l.getId(), l.getName()))
                .toList();
    }

    public List<CourseSearchDto> searchCourses(String query) {
        if (query == null || query.isBlank()) return List.of();
        return courseRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(c -> new CourseSearchDto(
                        c.getId(),
                        c.getName(),
                        null // Course에 List 연관관계가 생기면 c.getList().getId()로 교체
                ))
                .toList();
    }
}
