package com.example.UniversityERPSystem.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {

    private Long id;


    @NotNull(message = "Enrollment date cannot be null")
    @PastOrPresent(message = "Enrollment date cannot be in the future")
    private Date enrollmentDate;


    @NotBlank(message = "Enrollment status cannot be blank")
    @Size(max = 255, message = "Enrollment status cannot exceed 255 characters")
    private String status;


    @NotNull(message = "Student ID cannot be null")
    @Positive(message = "Student ID must be greater than zero")
    private Long studentId;


    @NotNull(message = "Course ID cannot be null")
    @Positive(message = "Course ID must be greater than zero")
    private Long courseId;


}