package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        // Call addDepartment from DepartmentService
        return departmentService.addDepartment(
                department,
                department.getFaculty()
        );
    }


    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        // Call getAllDepartments from DepartmentService
        return departmentService.getAllDepartments();
    }


    @GetMapping("/getById/{id}")
    public Department getById(@PathVariable Long id) {
        // Call getById from DepartmentService
        return departmentService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestBody Department department) {
        // Call updateDepartment from DepartmentService
        return departmentService.updateDepartment(
                id,
                department.getName(),
                department.getDescription(),
                department.getFaculty()
        );    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        // Call deleteById from DepartmentService
        return departmentService.deleteById(id);
    }
}