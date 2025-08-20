package com.example.lbs.repository;

import com.example.lbs.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> { }