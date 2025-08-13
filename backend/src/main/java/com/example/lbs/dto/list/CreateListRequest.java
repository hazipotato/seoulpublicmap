package com.example.lbs.dto.list;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateListRequest {
    /** 프론트가 listName으로 보내도 받고, name으로 보내도 받도록 처리 */
    @NotBlank
    @JsonAlias({"listName", "name"})
    private String name;
}