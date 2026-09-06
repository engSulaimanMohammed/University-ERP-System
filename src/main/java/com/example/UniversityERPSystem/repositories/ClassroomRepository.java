package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}