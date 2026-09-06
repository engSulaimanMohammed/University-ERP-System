package com.example.UniversityERPSystem.repositories;


import com.example.UniversityERPSystem.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
