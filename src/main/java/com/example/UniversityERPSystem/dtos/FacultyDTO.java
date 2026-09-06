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
public class FacultyDTO {

    private Long id;


    @NotBlank(message = "Faculty name cannot be blank")
    @Size(max = 255, message = "Faculty name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Faculty description cannot be blank")
    @Size(max = 255, message = "Faculty description cannot exceed 255 characters")
    private String description;


    @NotNull(message = "University ID cannot be null")
    @Positive(message = "University ID must be greater than zero")
    private Long universityId;


}