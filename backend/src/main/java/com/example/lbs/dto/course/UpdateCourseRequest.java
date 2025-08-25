package com.example.lbs.dto.course;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCourseRequest {
    @NotBlank
    @JsonAlias({"courseName","name"})
    private String name;

    @Size(max = 500)
    private String explanation; // null이면 변경 안 함
}
