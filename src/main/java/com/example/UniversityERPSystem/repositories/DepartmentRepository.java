package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}