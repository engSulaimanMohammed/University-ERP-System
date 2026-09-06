package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.CourseAverageDTO;
import com.example.UniversityERPSystem.dtos.GradeDTO;
import com.example.UniversityERPSystem.dtos.StudentGradeStatsDTO;
import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.services.GradeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;


    // Constructor Injection.
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    // Add and record a new Grade.
    @PostMapping("/add")
    public GradeDTO addGrade(
            @Valid @RequestBody GradeDTO gradeDTO) {

        // Create Grade Entity from DTO.
        Grade grade = new Grade();

        grade.setScore(gradeDTO.getScore());
        grade.setLetterGrade(gradeDTO.getLetterGrade());

        // Save Grade using Enrollment ID and Exam ID.
        Grade savedGrade = gradeService.addGrade(
                grade,
                gradeDTO.getEnrollmentId(),
                gradeDTO.getExamId()
        );

        // Return DTO instead of raw Entity.
        return GradeDTO.convertToDTO(savedGrade);
    }


    // Get all active Grades.
    @GetMapping("/getAll")
    public List<GradeDTO> getAllGrades() {

        return GradeDTO.convertToDTO(
                gradeService.getAllGrades()
        );
    }


    // Get active Grade by ID.
    @GetMapping("/getById/{id}")
    public GradeDTO getById(
            @PathVariable Long id) {

        Grade grade = gradeService.getById(id);

        return GradeDTO.convertToDTO(grade);
    }


    // Update an existing Grade.
    @PutMapping("/update/{id}")
    public GradeDTO updateGrade(
            @PathVariable Long id,
            @Valid @RequestBody GradeDTO gradeDTO) {

        Grade updatedGrade = gradeService.updateGrade(
                id,
                gradeDTO.getScore(),
                gradeDTO.getLetterGrade(),
                gradeDTO.getEnrollmentId(),
                gradeDTO.getExamId()
        );

        // Return updated Grade as DTO.
        return GradeDTO.convertToDTO(updatedGrade);
    }


    // Get all active Grades for a specific Student.
    @GetMapping("/byStudent/{studentId}")
    public List<GradeDTO> getGradesByStudent(
            @PathVariable Long studentId) {

        return GradeDTO.convertToDTO(
                gradeService.getGradesByStudent(studentId)
        );
    }


    // Get Student average score and GPA-style classification.
    @GetMapping("/studentStats/{studentId}")
    public StudentGradeStatsDTO getStudentGradeStats(
            @PathVariable Long studentId) {

        double averageScore =
                gradeService.getStudentAverageScore(studentId);

        String classification =
                gradeService.getStudentGpaClassification(studentId);

        return StudentGradeStatsDTO.builder()
                .studentId(studentId)
                .averageScore(averageScore)
                .gpaClassification(classification)
                .build();
    }


    // Get average Grade score for a Course.
    @GetMapping("/courseAverage/{courseId}")
    public CourseAverageDTO getCourseAverage(
            @PathVariable Long courseId) {

        double averageScore =
                gradeService.getCourseAverageScore(courseId);

        return CourseAverageDTO.builder()
                .courseId(courseId)
                .averageScore(averageScore)
                .build();
    }


    // Soft delete Grade by ID.
    @DeleteMapping("/delete/{id}")
    public GradeDTO deleteById(
            @PathVariable Long id) {

        // Get Grade before Soft Delete.
        GradeDTO gradeDTO = GradeDTO.convertToDTO(
                gradeService.getById(id)
        );

        // Perform Soft Delete.
        gradeService.deleteById(id);

        // Return DTO.
        return gradeDTO;
    }
}