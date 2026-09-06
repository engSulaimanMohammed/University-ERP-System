package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.dtos.UniversityStatsDTO;
import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.DepartmentRepository;
import com.example.UniversityERPSystem.repositories.FacultyRepository;
import com.example.UniversityERPSystem.repositories.StudentRepository;
import com.example.UniversityERPSystem.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;


    // Constructor Injection.
    public UniversityService(UniversityRepository universityRepository,
                             FacultyRepository facultyRepository,
                             DepartmentRepository departmentRepository,
                             StudentRepository studentRepository) {

        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }


    // Validate University fields.
    private void validateUniversityData(String name,
                                        String location) {

        // Validate University name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "University name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "University name cannot exceed 255 characters"
            );
        }


        // Validate University location.
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "University location cannot be blank"
            );
        }

        if (location.length() > 255) {
            throw new IllegalArgumentException(
                    "University location cannot exceed 255 characters"
            );
        }
    }


    // Add a new University.
    public University addUniversity(University university) {

        if (university == null) {
            throw new IllegalArgumentException(
                    "University cannot be null"
            );
        }

        validateUniversityData(
                university.getName(),
                university.getLocation()
        );

        university.setActive(true);
        university.setCreatedDate(new Date());

        return universityRepository.save(university);
    }


    // Get all active Universities.
    public List<University> getAllUniversities() {

        return universityRepository.findAll()
                .stream()
                .filter(University::isActive)
                .toList();
    }


    // Get active University by ID.
    public University getById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "University ID must be greater than zero"
            );
        }

        University university =
                universityRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "University not found with id: " + id
                                )
                        );

        if (!university.isActive()) {
            throw new ResourceNotFoundException(
                    "University not found with id: " + id
            );
        }

        return university;
    }


    // Update an existing University.
    public University updateUniversity(Long id,
                                       String name,
                                       String location) {

        validateUniversityData(name, location);

        University universityToUpdate = getById(id);

        universityToUpdate.setName(name);
        universityToUpdate.setLocation(location);
        universityToUpdate.setUpdatedDate(new Date());

        return universityRepository.save(universityToUpdate);
    }


    // Get University statistics.
    public UniversityStatsDTO getUniversityStats(Long universityId) {

        // Make sure University exists and is active.
        University university = getById(universityId);

        long faculties =
                facultyRepository
                        .countByUniversity_IdAndIsActiveTrue(
                                universityId
                        );

        long departments =
                departmentRepository
                        .countActiveDepartmentsByUniversity(
                                universityId
                        );

        long students =
                studentRepository
                        .countActiveStudentsByUniversity(
                                universityId
                        );

        return UniversityStatsDTO.builder()
                .universityId(university.getId())
                .universityName(university.getName())
                .totalActiveFaculties(faculties)
                .totalActiveDepartments(departments)
                .totalActiveStudents(students)
                .build();
    }


    // Soft delete University by ID.
    public Boolean deleteById(Long id) {

        University universityToDelete = getById(id);

        universityToDelete.setActive(false);
        universityToDelete.setUpdatedDate(new Date());

        universityRepository.save(universityToDelete);

        return true;
    }
}