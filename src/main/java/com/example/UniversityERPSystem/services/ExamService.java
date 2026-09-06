package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final CourseService courseService;


    // Constructor Injection.
    public ExamService(ExamRepository examRepository,
                       CourseService courseService) {

        this.examRepository = examRepository;
        this.courseService = courseService;
    }


    // Validate Exam fields.
    private void validateExamData(String title,
                                  Date examDate,
                                  double totalMarks) {

        // Validate Exam title.
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Exam title cannot be blank"
            );
        }

        if (title.length() > 255) {
            throw new IllegalArgumentException(
                    "Exam title cannot exceed 255 characters"
            );
        }


        // Validate Exam date.
        if (examDate == null) {
            throw new IllegalArgumentException(
                    "Exam date cannot be null"
            );
        }

        // Exam date must be in the future.
        if (!examDate.after(new Date())) {
            throw new IllegalArgumentException(
                    "Exam date must be in the future"
            );
        }


        // Validate Total Marks.
        if (totalMarks <= 0) {
            throw new IllegalArgumentException(
                    "Total marks must be greater than zero"
            );
        }
    }


    // Add a new Exam.
    public Exam addExam(Exam exam,
                        Long courseId) {

        // Use the same business logic as scheduling an Exam.
        return scheduleExam(exam, courseId);
    }


    // Schedule an Exam under a Course.
    public Exam scheduleExam(Exam exam,
                             Long courseId) {

        if (exam == null) {
            throw new IllegalArgumentException(
                    "Exam cannot be null"
            );
        }

        // Validate Exam data.
        validateExamData(
                exam.getTitle(),
                exam.getExamDate(),
                exam.getTotalMarks()
        );

        // Get active Course.
        Course course = courseService.getById(courseId);

        // Set Course relationship.
        exam.setCourse(course);

        // Set BaseClass fields.
        exam.setActive(true);
        exam.setCreatedDate(new Date());

        // Save Exam.
        return examRepository.save(exam);
    }


    // Get all active Exams.
    public List<Exam> getAllExams() {

        return examRepository.findAll()
                .stream()
                .filter(Exam::isActive)
                .toList();
    }


    // Get active Exam by ID.
    public Exam getById(Long id) {

        // Validate Exam ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Exam ID must be greater than zero"
            );
        }

        // Find Exam.
        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Exam not found with id: " + id
                        )
                );

        // Do not return Soft Deleted Exam.
        if (!exam.isActive()) {
            throw new ResourceNotFoundException(
                    "Exam not found with id: " + id
            );
        }

        return exam;
    }


    // Update an existing Exam.
    public Exam updateExam(Long id,
                           String title,
                           Date examDate,
                           double totalMarks,
                           Long courseId) {

        // Validate new Exam data.
        validateExamData(
                title,
                examDate,
                totalMarks
        );

        // Get active Exam.
        Exam examToUpdate = getById(id);

        // Get active Course.
        Course course = courseService.getById(courseId);

        // Update Exam fields.
        examToUpdate.setTitle(title);
        examToUpdate.setExamDate(examDate);
        examToUpdate.setTotalMarks(totalMarks);

        // Update Course relationship.
        examToUpdate.setCourse(course);

        // Update modification date.
        examToUpdate.setUpdatedDate(new Date());

        // Save updated Exam.
        return examRepository.save(examToUpdate);
    }


    // Get all active Exams for a specific Course.
    public List<Exam> getExamsByCourse(Long courseId) {

        // Make sure Course exists and is active.
        courseService.getById(courseId);

        return examRepository
                .findByCourse_IdAndIsActiveTrue(courseId);
    }


    // Soft delete Exam by ID.
    public Boolean deleteById(Long id) {

        // Get active Exam.
        Exam examToDelete = getById(id);

        // Soft Delete.
        examToDelete.setActive(false);

        // Update modification date.
        examToDelete.setUpdatedDate(new Date());

        // Save changes.
        examRepository.save(examToDelete);

        return true;
    }
}