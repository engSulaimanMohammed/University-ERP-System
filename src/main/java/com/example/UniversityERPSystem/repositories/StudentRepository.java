package com.example.UniversityERPSystem.repositories;


import com.example.UniversityERPSystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
