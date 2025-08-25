package com.example.lbs.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CourseSummaryDto {
    private Long id;
    private String name;
    private String explanation;
}
