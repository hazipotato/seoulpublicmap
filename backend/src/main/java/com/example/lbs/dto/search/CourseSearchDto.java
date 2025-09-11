package com.example.lbs.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CourseSearchDto {
    private Long id;
    private String name;
    private Long listId; // 현재 Course가 List에 연관 없으면 null로 반환
}
