package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyService facultyService;

    public DepartmentService(DepartmentRepository departmentRepository,
                             FacultyService facultyService) {
        this.departmentRepository = departmentRepository;
        this.facultyService = facultyService;
    }


    // Validate Department fields.
    private void validateDepartmentData(String name, String description) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Department name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Department name cannot exceed 255 characters"
            );
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Department description cannot be blank"
            );
        }

        if (description.length() > 255) {
            throw new IllegalArgumentException(
                    "Department description cannot exceed 255 characters"
            );
        }
    }



    // Add a new Department.
    public Department addDepartment(Department department, Long facultyId) {

        if (department == null) {
            throw new IllegalArgumentException(
                    "Department cannot be null"
            );
        }

        // Validate Department fields.
        validateDepartmentData(
                department.getName(),
                department.getDescription()
        );

        // Get active Faculty.
        Faculty faculty = facultyService.getById(facultyId);

        department.setFaculty(faculty);
        department.setActive(true);
        department.setCreatedDate(new Date());

        return departmentRepository.save(department);
    }



    // Get all active Departments.
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .filter(Department::isActive)
                .toList();
    }






}