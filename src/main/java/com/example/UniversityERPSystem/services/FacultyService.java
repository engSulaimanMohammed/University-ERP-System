package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;

    public FacultyService(FacultyRepository facultyRepository,
                          UniversityService universityService) {
        this.facultyRepository = facultyRepository;
        this.universityService = universityService;
    }


    // Validate Faculty fields.
    private void validateFacultyData(String name, String description) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Faculty name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Faculty name cannot exceed 255 characters"
            );
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Faculty description cannot be blank"
            );
        }

        if (description.length() > 255) {
            throw new IllegalArgumentException(
                    "Faculty description cannot exceed 255 characters"
            );
        }
    }




}