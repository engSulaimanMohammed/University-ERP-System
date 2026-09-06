package com.example.UniversityERPSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    private Long id;

    private double score;

    private String letterGrade;

    private Long enrollmentId;

    private Long examId;
}