package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.ExamDTO;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.services.ExamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;


    // Constructor Injection.
    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    // Add a new Exam.
    @PostMapping("/add")
    public ExamDTO addExam(
            @Valid @RequestBody ExamDTO examDTO) {

        // Create Exam Entity from DTO.
        Exam exam = new Exam();

        exam.setTitle(examDTO.getTitle());
        exam.setExamDate(examDTO.getExamDate());
        exam.setTotalMarks(examDTO.getTotalMarks());

        // Save Exam using Course ID from DTO.
        Exam savedExam = examService.addExam(
                exam,
                examDTO.getCourseId()
        );

        // Return DTO instead of raw Entity.
        return ExamDTO.convertToDTO(savedExam);
    }


    // Schedule a new Exam under a Course.
    @PostMapping("/schedule")
    public ExamDTO scheduleExam(
            @Valid @RequestBody ExamDTO examDTO) {

        // Create Exam Entity from DTO.
        Exam exam = new Exam();

        exam.setTitle(examDTO.getTitle());
        exam.setExamDate(examDTO.getExamDate());
        exam.setTotalMarks(examDTO.getTotalMarks());

        // Schedule Exam using Course ID from DTO.
        Exam scheduledExam = examService.scheduleExam(
                exam,
                examDTO.getCourseId()
        );

        // Return DTO instead of raw Entity.
        return ExamDTO.convertToDTO(scheduledExam);
    }


    // Get all active Exams.
    @GetMapping("/getAll")
    public List<ExamDTO> getAllExams() {

        // Convert List of Exam Entities to DTOs.
        return ExamDTO.convertToDTO(
                examService.getAllExams()
        );
    }


    // Get active Exam by ID.
    @GetMapping("/getById/{id}")
    public ExamDTO getById(
            @PathVariable Long id) {

        // Get Exam from Service.
        Exam exam = examService.getById(id);

        // Convert Entity to DTO.
        return ExamDTO.convertToDTO(exam);
    }


    // Update an existing Exam.
    @PutMapping("/update/{id}")
    public ExamDTO updateExam(
            @PathVariable Long id,
            @Valid @RequestBody ExamDTO examDTO) {

        // Update Exam using DTO data.
        Exam updatedExam = examService.updateExam(
                id,
                examDTO.getTitle(),
                examDTO.getExamDate(),
                examDTO.getTotalMarks(),
                examDTO.getCourseId()
        );

        // Return updated Exam as DTO.
        return ExamDTO.convertToDTO(updatedExam);
    }


    // Get all active Exams for a specific Course.
    @GetMapping("/byCourse/{courseId}")
    public List<ExamDTO> getExamsByCourse(
            @PathVariable Long courseId) {

        return ExamDTO.convertToDTO(
                examService.getExamsByCourse(courseId)
        );
    }


    // Soft delete Exam by ID.
    @DeleteMapping("/delete/{id}")
    public ExamDTO deleteById(
            @PathVariable Long id) {

        // Get Exam before Soft Delete.
        ExamDTO examDTO = ExamDTO.convertToDTO(
                examService.getById(id)
        );

        // Perform Soft Delete.
        examService.deleteById(id);

        // Return DTO instead of raw Entity.
        return examDTO;
    }
}