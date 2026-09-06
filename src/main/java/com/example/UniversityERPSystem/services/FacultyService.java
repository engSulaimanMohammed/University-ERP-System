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


    // Add a new Faculty.
    public Faculty addFaculty(Faculty faculty, Long universityId) {

        if (faculty == null) {
            throw new IllegalArgumentException(
                    "Faculty cannot be null"
            );
        }
        // Validate Faculty fields.
        validateFacultyData(
                faculty.getName(),
                faculty.getDescription()
        );
        // Get an active University.
        University university = universityService.getById(universityId);
        faculty.setUniversity(university);
        faculty.setActive(true);
        faculty.setCreatedDate(new Date());
        return facultyRepository.save(faculty);
    }


    // Get all active Faculties.
    public List<Faculty> getAllFaculties() {

        return facultyRepository.findAll()
                .stream()
                .filter(Faculty::isActive)
                .toList();
    }







}