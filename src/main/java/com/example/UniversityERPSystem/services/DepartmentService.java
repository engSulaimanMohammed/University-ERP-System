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


    // Get active Department by ID.
    public Department getById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Department ID must be greater than zero"
            );
        }

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        // Soft-deleted Departments must not be returned.
        if (!department.isActive()) {
            throw new ResourceNotFoundException(
                    "Department not found with id: " + id
            );
        }

        return department;
    }


    // Update an existing Department.
    public Department updateDepartment(Long id,
                                       String name,
                                       String description,
                                       Long facultyId) {

        // Validate new Department data.
        validateDepartmentData(name, description);

        // Get active Department.
        Department departmentToUpdate = getById(id);

        // Get active Faculty.
        Faculty faculty = facultyService.getById(facultyId);

        departmentToUpdate.setName(name);
        departmentToUpdate.setDescription(description);
        departmentToUpdate.setFaculty(faculty);
        departmentToUpdate.setUpdatedDate(new Date());

        return departmentRepository.save(departmentToUpdate);
    }


    // Soft delete Department by ID.
    public Boolean deleteById(Long id) {

        Department departmentToDelete = getById(id);

        departmentToDelete.setActive(false);
        departmentToDelete.setUpdatedDate(new Date());

        departmentRepository.save(departmentToDelete);

        return true;
    }
}