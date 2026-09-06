package com.example.UniversityERPSystem.repositories;


import com.example.UniversityERPSystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}