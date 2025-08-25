package com.example.lbs.dto.course;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCourseRequest {
    @NotBlank
    @JsonAlias({"courseName","name"}) // 프론트가 courseName이나 name으로 보내도 수용
    private String name;

    @Size(max = 500)
    private String explanation; // null 가능
}
