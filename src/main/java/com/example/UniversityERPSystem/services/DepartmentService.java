package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<Department> activeDepartments = new ArrayList<>();
        for (Department department : departments) {
            if (department.isActive()) {
                activeDepartments.add(department);
            }
        }
        return activeDepartments;
    }













}
