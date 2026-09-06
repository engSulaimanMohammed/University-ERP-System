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
        return null;
    }


    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        // Call getAllDepartments from DepartmentService
        return null;
    }


    @GetMapping("/getById/{id}")
    public Department getById(@PathVariable Long id) {
        // Call getById from DepartmentService
        return null;
    }











}