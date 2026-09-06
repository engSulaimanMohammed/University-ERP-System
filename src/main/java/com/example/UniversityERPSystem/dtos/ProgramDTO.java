package com.example.UniversityERPSystem.dtos;

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

    private String name;

    private String degreeLevel;

    private int durationYears;

    private Long departmentId;
}