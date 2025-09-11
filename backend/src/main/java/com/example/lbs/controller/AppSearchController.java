package com.example.lbs.controller;

import com.example.lbs.dto.search.CourseSearchDto;
import com.example.lbs.dto.search.ListSearchDto;
import com.example.lbs.service.AppSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class AppSearchController {

    private final AppSearchService searchService;

    // GET /api/search/lists?query=검색어 -> 응답 [{ id, name }]
    @GetMapping("/lists")
    public List<ListSearchDto> searchLists(
            @RequestParam(name = "query", required = false, defaultValue = "") String query
    ) {
        return searchService.searchLists(query);
    }

    // GET /api/search/courses?query=검색어 -> 응답 [{ id, name, listId }]... 리스트 아이디는 왜 필요하지..
    @GetMapping("/courses")
    public List<CourseSearchDto> searchCourses(
            @RequestParam(name = "query", required = false, defaultValue = "") String query
    ) {
        return searchService.searchCourses(query);
    }
}
