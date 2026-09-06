package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
