package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}