package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository
        extends JpaRepository<Student, Long> {


    // Count active Students in an active University.
    @Query("""
            SELECT COUNT(s)
            FROM Student s
            WHERE s.program.department.faculty.university.id = :universityId
            AND s.isActive = true
            AND s.program.isActive = true
            AND s.program.department.isActive = true
            AND s.program.department.faculty.isActive = true
            AND s.program.department.faculty.university.isActive = true
            """)
    long countActiveStudentsByUniversity(
            @Param("universityId") Long universityId
    );
}