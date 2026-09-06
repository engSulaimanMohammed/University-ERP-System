package com.example.UniversityERPSystem.repositories;

import com.example.UniversityERPSystem.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {


    // Check if a Grade already exists
    // for the same Enrollment and Exam.
    boolean existsByEnrollment_IdAndExam_IdAndIsActiveTrue(
            Long enrollmentId,
            Long examId
    );


    // Get all active Grades for a specific Student.
    @Query("""
            SELECT g
            FROM Grade g
            WHERE g.enrollment.student.id = :studentId
            AND g.isActive = true
            AND g.enrollment.isActive = true
            AND g.enrollment.student.isActive = true
            AND g.exam.isActive = true
            """)
    List<Grade> findActiveGradesByStudent(
            @Param("studentId") Long studentId
    );


    // Calculate the average score for a Student.
    @Query("""
            SELECT AVG(g.score)
            FROM Grade g
            WHERE g.enrollment.student.id = :studentId
            AND g.isActive = true
            AND g.enrollment.isActive = true
            AND g.enrollment.student.isActive = true
            AND g.exam.isActive = true
            """)
    Double findAverageScoreByStudent(
            @Param("studentId") Long studentId
    );


    // Calculate the average Exam score for a Course.
    @Query("""
            SELECT AVG(g.score)
            FROM Grade g
            WHERE g.exam.course.id = :courseId
            AND g.isActive = true
            AND g.exam.isActive = true
            AND g.exam.course.isActive = true
            AND g.enrollment.isActive = true
            """)
    Double findAverageScoreByCourse(
            @Param("courseId") Long courseId
    );
}