package com.example.lbs.service;

import com.example.lbs.common.ApiException;
import com.example.lbs.domain.Course;
import com.example.lbs.dto.course.CourseSummaryDto;
import com.example.lbs.dto.course.CreateCourseRequest;
import com.example.lbs.dto.course.UpdateCourseRequest;
import com.example.lbs.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
public class CourseService {
    private final CourseRepository repo;

    public CourseSummaryDto create(CreateCourseRequest req) {
        Course saved = repo.save(Course.builder()
                .name(req.getName())
                .explanation(req.getExplanation())
                .build());
        return new CourseSummaryDto(saved.getId(), saved.getName(), saved.getExplanation());
    }

    @Transactional(readOnly = true)
    public List<CourseSummaryDto> findAll() {
        return repo.findAll().stream()
                .map(c -> new CourseSummaryDto(c.getId(), c.getName(), c.getExplanation()))
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseSummaryDto findOne(Long id) {
        Course c = repo.findById(id).orElseThrow(() -> new ApiException(404, "코스가 없습니다."));
        return new CourseSummaryDto(c.getId(), c.getName(), c.getExplanation());
    }

    public CourseSummaryDto update(Long id, UpdateCourseRequest req) {
        Course c = repo.findById(id).orElseThrow(() -> new ApiException(404, "코스가 없습니다."));
        c.setName(req.getName());
        if (req.getExplanation() != null) c.setExplanation(req.getExplanation());
        return new CourseSummaryDto(c.getId(), c.getName(), c.getExplanation());
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new ApiException(404, "코스가 없습니다.");
        repo.deleteById(id);
    }
}
