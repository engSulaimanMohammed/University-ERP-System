package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.DepartmentDTO;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    // Constructor Injection.
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    // Add a new Department.
    @PostMapping("/add")
    public DepartmentDTO addDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        // Create Department Entity from DTO.
        Department department = new Department();

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        // Save Department using Faculty ID from DTO.
        Department savedDepartment = departmentService.addDepartment(
                department,
                departmentDTO.getFacultyId()
        );

        // Return DTO instead of raw Entity.
        return DepartmentDTO.convertToDTO(savedDepartment);
    }


    // Get all active Departments.
    @GetMapping("/getAll")
    public List<DepartmentDTO> getAllDepartments() {

        return DepartmentDTO.convertToDTO(
                departmentService.getAllDepartments()
        );
    }


    // Get active Department by ID.
    @GetMapping("/getById/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {

        Department department = departmentService.getById(id);

        return DepartmentDTO.convertToDTO(department);
    }


    // Update an existing Department.
    @PutMapping("/update/{id}")
    public DepartmentDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        Department updatedDepartment = departmentService.updateDepartment(
                id,
                departmentDTO.getName(),
                departmentDTO.getDescription(),
                departmentDTO.getFacultyId()
        );

        // Return updated Department as DTO.
        return DepartmentDTO.convertToDTO(updatedDepartment);
    }


    // Soft delete Department by ID.
    @DeleteMapping("/delete/{id}")
    public DepartmentDTO deleteById(@PathVariable Long id) {

        // Get Department before soft deleting it.
        DepartmentDTO departmentDTO = DepartmentDTO.convertToDTO(
                departmentService.getById(id)
        );

        // Perform soft delete.
        departmentService.deleteById(id);

        // Return DTO instead of raw Entity.
        return departmentDTO;
    }
}