package com.example.UniversityERPSystem.dtos;


import com.example.UniversityERPSystem.entities.Exam;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private Long id;


    @NotBlank(message = "Exam title cannot be blank")
    @Size(max = 255, message = "Exam title cannot exceed 255 characters")
    private String title;


    @NotNull(message = "Exam date cannot be null")
    @Future(message = "Exam date must be in the future")
    private Date examDate;


    @Positive(message = "Total marks must be greater than zero")
    private double totalMarks;


    @NotNull(message = "Course ID cannot be null")
    @Positive(message = "Course ID must be greater than zero")
    private Long courseId;


    // Convert one Exam Entity to ExamDTO.
    public static ExamDTO convertToDTO(Exam exam) {

        return ExamDTO.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .examDate(exam.getExamDate())
                .totalMarks(exam.getTotalMarks())
                .courseId(
                        exam.getCourse() != null
                                ? exam.getCourse().getId()
                                : null
                )
                .build();
    }




    // Convert List of Exams to List of ExamDTOs.
    public static List<ExamDTO> convertToDTO(List<Exam> exams) {

        return exams.stream()
                .map(ExamDTO::convertToDTO)
                .toList();
    }
}