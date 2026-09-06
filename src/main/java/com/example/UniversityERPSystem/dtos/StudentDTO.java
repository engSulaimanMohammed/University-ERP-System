package com.example.UniversityERPSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String major;

    private Long programId;
}