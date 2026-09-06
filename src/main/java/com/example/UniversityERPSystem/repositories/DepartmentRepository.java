package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {


    // Count active Departments in an active University.
    @Query("""
            SELECT COUNT(d)
            FROM Department d
            WHERE d.faculty.university.id = :universityId
            AND d.isActive = true
            AND d.faculty.isActive = true
            AND d.faculty.university.isActive = true
            """)
    long countActiveDepartmentsByUniversity(
            @Param("universityId") Long universityId
    );
}