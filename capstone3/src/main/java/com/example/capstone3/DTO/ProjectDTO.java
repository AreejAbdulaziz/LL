package com.example.capstone3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDTO {
    @NotNull(message = "team id cannot be null")
    @Positive(message = "enter correct team id")
    private Integer team_id;
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String name;
    @NotEmpty(message = "field Must be  not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String idea;
}
