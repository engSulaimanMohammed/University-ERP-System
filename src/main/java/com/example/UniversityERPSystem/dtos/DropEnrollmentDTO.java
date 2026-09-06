package com.example.UniversityERPSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DropEnrollmentDTO {

    @NotNull(message = "Enrollment ID cannot be null")
    @Positive(message = "Enrollment ID must be greater than zero")
    private Long enrollmentId;
}