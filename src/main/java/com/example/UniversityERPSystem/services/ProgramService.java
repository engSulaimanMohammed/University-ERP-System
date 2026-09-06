package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final DepartmentService departmentService;


    // Constructor Injection.
    public ProgramService(ProgramRepository programRepository,
                          DepartmentService departmentService) {

        this.programRepository = programRepository;
        this.departmentService = departmentService;
    }


    // Validate Program fields.
    private void validateProgramData(String name,
                                     String degreeLevel,
                                     int durationYears) {

        // Validate Program name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Program name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Program name cannot exceed 255 characters"
            );
        }


        // Validate Degree Level.
        if (degreeLevel == null || degreeLevel.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Degree level cannot be blank"
            );
        }

        if (degreeLevel.length() > 255) {
            throw new IllegalArgumentException(
                    "Degree level cannot exceed 255 characters"
            );
        }


        // Validate Duration Years.
        if (durationYears <= 0) {
            throw new IllegalArgumentException(
                    "Duration years must be greater than zero"
            );
        }
    }


    // Add a new Program.
    public Program addProgram(Program program, Long departmentId) {

        if (program == null) {
            throw new IllegalArgumentException(
                    "Program cannot be null"
            );
        }

        // Validate Program data.
        validateProgramData(
                program.getName(),
                program.getDegreeLevel(),
                program.getDurationYears()
        );

        // Get active Department.
        Department department = departmentService.getById(departmentId);

        // Set relationship.
        program.setDepartment(department);

        // Set BaseClass fields.
        program.setActive(true);
        program.setCreatedDate(new Date());

        // Save Program.
        return programRepository.save(program);
    }


    // Get all active Programs.
    public List<Program> getAllPrograms() {

        return programRepository.findAll()
                .stream()
                .filter(Program::isActive)
                .toList();
    }


    // Get active Program by ID.
    public Program getById(Long id) {

        // Validate ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Program ID must be greater than zero"
            );
        }

        // Find Program.
        Program program = programRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Program not found with id: " + id
                        )
                );

        // Do not return soft-deleted Program.
        if (!program.isActive()) {
            throw new ResourceNotFoundException(
                    "Program not found with id: " + id
            );
        }

        return program;
    }


    // Update an existing Program.
    public Program updateProgram(Long id,
                                 String name,
                                 String degreeLevel,
                                 int durationYears,
                                 Long departmentId) {

        // Validate new Program data.
        validateProgramData(
                name,
                degreeLevel,
                durationYears
        );

        // Get active Program.
        Program programToUpdate = getById(id);

        // Get active Department.
        Department department = departmentService.getById(departmentId);

        // Update Program fields.
        programToUpdate.setName(name);
        programToUpdate.setDegreeLevel(degreeLevel);
        programToUpdate.setDurationYears(durationYears);

        // Update relationship.
        programToUpdate.setDepartment(department);

        // Update modification date.
        programToUpdate.setUpdatedDate(new Date());

        // Save updated Program.
        return programRepository.save(programToUpdate);
    }


    // Soft delete Program by ID.
    public Boolean deleteById(Long id) {

        // Get active Program.
        Program programToDelete = getById(id);

        // Soft Delete.
        programToDelete.setActive(false);

        // Update modification date.
        programToDelete.setUpdatedDate(new Date());

        // Save changes.
        programRepository.save(programToDelete);

        return true;
    }
}