package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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



    public Department getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent() && department.get().isActive()) {
            return department.get();
        }
        return null;
    }


    public Department updateDepartment(Long id, String name, String description, Faculty faculty) {
        Department departmentToUpdate = getById(id);
        if (departmentToUpdate == null) {
            return null;
        }
        departmentToUpdate.setName(name);
        departmentToUpdate.setDescription(description);
        departmentToUpdate.setFaculty(faculty);
        departmentToUpdate.setUpdatedDate(new Date());
        return departmentRepository.save(departmentToUpdate);
    }




    public Boolean deleteById(Long id) {
        Department departmentToDelete = getById(id);
        if (departmentToDelete == null) {
            return false;
        }
        departmentToDelete.setActive(false);
        departmentToDelete.setUpdatedDate(new Date());
        departmentRepository.save(departmentToDelete);
        return true;
    }
}
