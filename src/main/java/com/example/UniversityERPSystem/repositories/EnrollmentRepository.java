package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {


    // Check if Student is already actively enrolled in a Course.
    boolean existsByStudent_IdAndCourse_IdAndIsActiveTrue(
            Long studentId,
            Long courseId
    );


    // Count active Enrollments in a Course.
    // Used when checking Course capacity.
    long countByCourse_IdAndIsActiveTrue(Long courseId);


    // Get all active Courses a Student is enrolled in.
    @Query("""
            SELECT DISTINCT e.course
            FROM Enrollment e
            WHERE e.student.id = :studentId
            AND e.isActive = true
            AND e.student.isActive = true
            AND e.course.isActive = true
            """)
    List<Course> findActiveCoursesByStudent(
            @Param("studentId") Long studentId
    );


    // Get all active Students enrolled in a Course.
    @Query("""
            SELECT DISTINCT e.student
            FROM Enrollment e
            WHERE e.course.id = :courseId
            AND e.isActive = true
            AND e.student.isActive = true
            AND e.course.isActive = true
            """)
    List<Student> findActiveStudentsByCourse(
            @Param("courseId") Long courseId
    );


    // Get active Enrollments by status.
    @Query("""
            SELECT e
            FROM Enrollment e
            WHERE LOWER(e.status) = LOWER(:status)
            AND e.isActive = true
            """)
    List<Enrollment> findActiveEnrollmentsByStatus(
            @Param("status") String status
    );


    // Count distinct active enrolled Students in a Program.
    @Query("""
            SELECT COUNT(DISTINCT e.student.id)
            FROM Enrollment e
            WHERE e.student.program.id = :programId
            AND e.isActive = true
            AND e.student.isActive = true
            AND e.course.isActive = true
            """)
    long countActiveEnrolledStudentsByProgram(
            @Param("programId") Long programId
    );
}