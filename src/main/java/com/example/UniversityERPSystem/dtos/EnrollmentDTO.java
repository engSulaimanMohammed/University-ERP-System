package com.example.UniversityERPSystem.dtos;

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

    private Date enrollmentDate;

    private String status;

    private Long studentId;

    private Long courseId;
}