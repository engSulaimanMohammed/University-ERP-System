package com.example.UniversityERPSystem.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {

    private Long id;


    @NotBlank(message = "Program name cannot be blank")
    @Size(max = 255, message = "Program name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Degree level cannot be blank")
    @Size(max = 255, message = "Degree level cannot exceed 255 characters")
    private String degreeLevel;


    @Positive(message = "Duration years must be greater than zero")
    private int durationYears;


    @NotNull(message = "Department ID cannot be null")
    @Positive(message = "Department ID must be greater than zero")
    private Long departmentId;
}