package com.example.UniversityERPSystem.dtos;


import com.example.UniversityERPSystem.entities.Grade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    private Long id;


    @PositiveOrZero(message = "Grade score cannot be negative")
    private double score;


    @NotBlank(message = "Letter grade cannot be blank")
    @Size(max = 255, message = "Letter grade cannot exceed 255 characters")
    private String letterGrade;


    @NotNull(message = "Enrollment ID cannot be null")
    @Positive(message = "Enrollment ID must be greater than zero")
    private Long enrollmentId;


    @NotNull(message = "Exam ID cannot be null")
    @Positive(message = "Exam ID must be greater than zero")
    private Long examId;


    // Convert one Grade Entity to GradeDTO.
    public static GradeDTO convertToDTO(Grade grade) {

        return GradeDTO.builder()
                .id(grade.getId())
                .score(grade.getScore())
                .letterGrade(grade.getLetterGrade())
                .enrollmentId(
                        grade.getEnrollment() != null
                                ? grade.getEnrollment().getId()
                                : null
                )
                .examId(
                        grade.getExam() != null
                                ? grade.getExam().getId()
                                : null
                )
                .build();
    }


    // Convert List of Grades to List of GradeDTOs.
    public static List<GradeDTO> convertToDTO(List<Grade> grades) {

        return grades.stream()
                .map(GradeDTO::convertToDTO)
                .toList();
    }





}