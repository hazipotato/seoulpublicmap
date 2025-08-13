package com.example.lbs.dto.list;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateListRequest {
    @NotBlank
    @JsonAlias({"listName", "name"})
    private String name;
}