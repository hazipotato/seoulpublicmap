package com.example.lbs.dto.list;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ListSummaryDto {
    private Long id;
    private String name;
    private String explanation;
}