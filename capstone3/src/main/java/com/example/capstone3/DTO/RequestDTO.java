package com.example.capstone3.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestDTO {
    @NotNull(message = "member id cannot be null")
    @Positive(message = "enter correct member id")
    private Integer member_id;
    @Positive(message = "enter correct team id")
    private Integer team_id;
}
