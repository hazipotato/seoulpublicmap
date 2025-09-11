package com.example.lbs.repository;

import java.util.*;
import com.example.lbs.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCase(String name); // ← 추가
}