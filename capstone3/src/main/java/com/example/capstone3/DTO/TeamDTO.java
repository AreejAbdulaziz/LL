package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "description cannot be null")
    private String description;
    private Integer maxCap;
}
