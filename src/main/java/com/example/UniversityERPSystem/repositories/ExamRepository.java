package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByCourse_IdAndIsActiveTrue(Long courseId);

}
