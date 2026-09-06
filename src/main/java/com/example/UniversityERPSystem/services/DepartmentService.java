package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department addDepartment(Department department, Faculty faculty) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        department.setActive(true);
        department.setCreatedDate(new Date());
        department.setFaculty(faculty);
        return departmentRepository.save(department);
    }










}
