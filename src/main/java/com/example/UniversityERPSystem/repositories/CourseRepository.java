package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    // Get all active Courses inside a specific Program.
    @Query("""
            SELECT c
            FROM Course c
            WHERE c.program.id = :programId
            AND c.isActive = true
            """)
    List<Course> findActiveCoursesByProgram(
            @Param("programId") Long programId
    );


    // Get all active Courses taught by a specific Instructor.
    @Query("""
            SELECT c
            FROM Course c
            WHERE c.instructor.id = :instructorId
            AND c.isActive = true
            """)
    List<Course> findActiveCoursesByInstructor(
            @Param("instructorId") Long instructorId
    );


    // Get all active Courses that do not have an Instructor.
    @Query("""
            SELECT c
            FROM Course c
            WHERE c.instructor IS NULL
            AND c.isActive = true
            """)
    List<Course> findActiveCoursesWithoutInstructor();
}