package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final EnrollmentService enrollmentService;
    private final ExamService examService;
    private final StudentService studentService;
    private final CourseService courseService;


    // Constructor Injection.
    public GradeService(GradeRepository gradeRepository,
                        EnrollmentService enrollmentService,
                        ExamService examService,
                        StudentService studentService,
                        CourseService courseService) {

        this.gradeRepository = gradeRepository;
        this.enrollmentService = enrollmentService;
        this.examService = examService;
        this.studentService = studentService;
        this.courseService = courseService;
    }


    // Validate Grade fields.
    private void validateGradeData(double score,
                                   String letterGrade) {

        // Score can be zero but cannot be negative.
        if (score < 0) {
            throw new IllegalArgumentException(
                    "Grade score cannot be negative"
            );
        }


        // Validate Letter Grade.
        if (letterGrade == null || letterGrade.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Letter grade cannot be blank"
            );
        }

        if (letterGrade.length() > 255) {
            throw new IllegalArgumentException(
                    "Letter grade cannot exceed 255 characters"
            );
        }
    }


    // Validate that the Exam belongs to the same Course
    // as the Enrollment.
    private void validateEnrollmentExamRelationship(
            Enrollment enrollment,
            Exam exam) {

        Long enrollmentCourseId =
                enrollment.getCourse().getId();

        Long examCourseId =
                exam.getCourse().getId();

        if (!enrollmentCourseId.equals(examCourseId)) {
            throw new IllegalArgumentException(
                    "Exam does not belong to the Enrollment course"
            );
        }
    }


    // Validate that score does not exceed Exam total marks.
    private void validateScoreAgainstExam(double score,
                                          Exam exam) {

        if (score > exam.getTotalMarks()) {
            throw new IllegalArgumentException(
                    "Grade score cannot exceed Exam total marks"
            );
        }
    }


    // Add and record a new Grade.
    public Grade addGrade(Grade grade,
                          Long enrollmentId,
                          Long examId) {

        if (grade == null) {
            throw new IllegalArgumentException(
                    "Grade cannot be null"
            );
        }

        // Validate Grade fields.
        validateGradeData(
                grade.getScore(),
                grade.getLetterGrade()
        );


        // Get active Enrollment.
        Enrollment enrollment =
                enrollmentService.getById(enrollmentId);


        // Get active Exam.
        Exam exam =
                examService.getById(examId);


        // Exam must belong to the Course
        // in which the Student is enrolled.
        validateEnrollmentExamRelationship(
                enrollment,
                exam
        );


        // Grade cannot exceed Exam total marks.
        validateScoreAgainstExam(
                grade.getScore(),
                exam
        );


        // Prevent duplicate Grade for
        // the same Enrollment and Exam.
        boolean gradeAlreadyExists =
                gradeRepository
                        .existsByEnrollment_IdAndExam_IdAndIsActiveTrue(
                                enrollmentId,
                                examId
                        );

        if (gradeAlreadyExists) {
            throw new IllegalArgumentException(
                    "Grade already exists for this Enrollment and Exam"
            );
        }


        // Set relationships.
        grade.setEnrollment(enrollment);
        grade.setExam(exam);

        // Set BaseClass fields.
        grade.setActive(true);
        grade.setCreatedDate(new Date());

        // Save Grade.
        return gradeRepository.save(grade);
    }


    // Get all active Grades.
    public List<Grade> getAllGrades() {

        return gradeRepository.findAll()
                .stream()
                .filter(Grade::isActive)
                .toList();
    }


    // Get active Grade by ID.
    public Grade getById(Long id) {

        // Validate Grade ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Grade ID must be greater than zero"
            );
        }


        // Find Grade.
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Grade not found with id: " + id
                        )
                );


        // Do not return Soft Deleted Grade.
        if (!grade.isActive()) {
            throw new ResourceNotFoundException(
                    "Grade not found with id: " + id
            );
        }

        return grade;
    }


    // Update an existing Grade.
    public Grade updateGrade(Long id,
                             double score,
                             String letterGrade,
                             Long enrollmentId,
                             Long examId) {

        // Validate Grade fields.
        validateGradeData(
                score,
                letterGrade
        );


        // Get active Grade.
        Grade gradeToUpdate = getById(id);


        // Get active Enrollment.
        Enrollment enrollment =
                enrollmentService.getById(enrollmentId);


        // Get active Exam.
        Exam exam =
                examService.getById(examId);


        // Validate relationship.
        validateEnrollmentExamRelationship(
                enrollment,
                exam
        );


        // Validate score.
        validateScoreAgainstExam(
                score,
                exam
        );


        // Check if Enrollment or Exam changed.
        boolean relationshipChanged =
                !gradeToUpdate
                        .getEnrollment()
                        .getId()
                        .equals(enrollmentId)
                        ||
                        !gradeToUpdate
                                .getExam()
                                .getId()
                                .equals(examId);


        // Prevent duplicate Grade during Update.
        if (relationshipChanged) {

            boolean gradeAlreadyExists =
                    gradeRepository
                            .existsByEnrollment_IdAndExam_IdAndIsActiveTrue(
                                    enrollmentId,
                                    examId
                            );

            if (gradeAlreadyExists) {
                throw new IllegalArgumentException(
                        "Grade already exists for this Enrollment and Exam"
                );
            }
        }


        // Update Grade fields.
        gradeToUpdate.setScore(score);
        gradeToUpdate.setLetterGrade(letterGrade);

        // Update relationships.
        gradeToUpdate.setEnrollment(enrollment);
        gradeToUpdate.setExam(exam);

        // Update modification date.
        gradeToUpdate.setUpdatedDate(new Date());

        // Save updated Grade.
        return gradeRepository.save(gradeToUpdate);
    }


    // Get all active Grades for a Student.
    public List<Grade> getGradesByStudent(Long studentId) {

        // Make sure Student exists and is active.
        studentService.getById(studentId);

        return gradeRepository
                .findActiveGradesByStudent(studentId);
    }


    // Calculate Student average score.
    public double getStudentAverageScore(Long studentId) {

        // Make sure Student exists and is active.
        studentService.getById(studentId);

        Double average =
                gradeRepository
                        .findAverageScoreByStudent(studentId);

        // Student has no Grades yet.
        if (average == null) {
            return 0.0;
        }

        return average;
    }


    // Calculate GPA-style classification.
    public String getStudentGpaClassification(Long studentId) {

        double average =
                getStudentAverageScore(studentId);


        /*
         * Project-defined GPA-style classification.
         * The project brief requires a GPA-style classification
         * but does not provide exact percentage boundaries.
         */
        if (average >= 90) {
            return "Excellent";
        }

        if (average >= 80) {
            return "Very Good";
        }

        if (average >= 70) {
            return "Good";
        }

        if (average >= 60) {
            return "Satisfactory";
        }

        return "Fail";
    }


    // Calculate average Grade score for a Course.
    public double getCourseAverageScore(Long courseId) {

        // Make sure Course exists and is active.
        courseService.getById(courseId);

        Double average =
                gradeRepository
                        .findAverageScoreByCourse(courseId);

        // Course has no Grades yet.
        if (average == null) {
            return 0.0;
        }

        return average;
    }


    // Soft delete Grade by ID.
    public Boolean deleteById(Long id) {

        // Get active Grade.
        Grade gradeToDelete = getById(id);

        // Soft Delete.
        gradeToDelete.setActive(false);

        // Update modification date.
        gradeToDelete.setUpdatedDate(new Date());

        // Save changes.
        gradeRepository.save(gradeToDelete);

        return true;
    }
}