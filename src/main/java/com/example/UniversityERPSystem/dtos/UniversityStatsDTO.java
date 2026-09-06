package com.example.UniversityERPSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityStatsDTO {

    private Long universityId;

    private String universityName;

    private long totalActiveFaculties;

    private long totalActiveDepartments;

    private long totalActiveStudents;
}