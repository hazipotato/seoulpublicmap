package com.example.lbs.controller;

import com.example.lbs.dto.course.CourseSummaryDto;
import com.example.lbs.dto.course.CreateCourseRequest;
import com.example.lbs.dto.course.UpdateCourseRequest;
import com.example.lbs.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseSummaryDto> getAll() {
        return courseService.findAll();
    }

    @PostMapping
    public ResponseEntity<CourseSummaryDto> create(@Valid @RequestBody CreateCourseRequest req) {
        CourseSummaryDto body = courseService.create(req);
        return ResponseEntity.created(URI.create("/api/courses/" + body.getId())).body(body);
    }

    @GetMapping("/{courseId}")
    public CourseSummaryDto getOne(@PathVariable Long courseId) {
        return courseService.findOne(courseId);
    }

    @PutMapping("/{courseId}")
    public CourseSummaryDto update(@PathVariable Long courseId,
                                   @Valid @RequestBody UpdateCourseRequest req) {
        return courseService.update(courseId, req);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.noContent().build();
    }
}
