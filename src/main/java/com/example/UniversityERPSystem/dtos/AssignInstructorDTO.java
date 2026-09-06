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
public class AssignInstructorDTO {

    @NotNull(message = "Course ID cannot be null")
    @Positive(message = "Course ID must be greater than zero")
    private Long courseId;

    @NotNull(message = "Instructor ID cannot be null")
    @Positive(message = "Instructor ID must be greater than zero")
    private Long instructorId;
}