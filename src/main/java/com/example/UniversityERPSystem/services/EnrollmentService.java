package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ProgramService programService;


    // Constructor Injection.
    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService,
                             ProgramService programService) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
        this.programService = programService;
    }


    // Validate Enrollment fields.
    private void validateEnrollmentData(Date enrollmentDate,
                                        String status) {

        // Validate Enrollment Date.
        if (enrollmentDate == null) {
            throw new IllegalArgumentException(
                    "Enrollment date cannot be null"
            );
        }

        // Enrollment Date cannot be in the future.
        if (enrollmentDate.after(new Date())) {
            throw new IllegalArgumentException(
                    "Enrollment date cannot be in the future"
            );
        }


        // Validate Enrollment Status.
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Enrollment status cannot be blank"
            );
        }

        if (status.length() > 255) {
            throw new IllegalArgumentException(
                    "Enrollment status cannot exceed 255 characters"
            );
        }
    }


    // Enroll a Student in a Course.
    public Enrollment addEnrollment(Enrollment enrollment,
                                    Long studentId,
                                    Long courseId) {

        if (enrollment == null) {
            throw new IllegalArgumentException(
                    "Enrollment cannot be null"
            );
        }

        // Validate Enrollment fields.
        validateEnrollmentData(
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );


        // Get active Student.
        Student student = studentService.getById(studentId);

        // Get active Course.
        Course course = courseService.getById(courseId);


        // Prevent duplicate Enrollment.
        boolean alreadyEnrolled =
                enrollmentRepository
                        .existsByStudent_IdAndCourse_IdAndIsActiveTrue(
                                studentId,
                                courseId
                        );

        if (alreadyEnrolled) {
            throw new IllegalArgumentException(
                    "Student is already enrolled in this course"
            );
        }


        // Set relationships.
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        // Set BaseClass fields.
        enrollment.setActive(true);
        enrollment.setCreatedDate(new Date());

        // Save Enrollment.
        return enrollmentRepository.save(enrollment);
    }


    // Get all active Enrollments.
    public List<Enrollment> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .filter(Enrollment::isActive)
                .toList();
    }


    // Get active Enrollment by ID.
    public Enrollment getById(Long id) {

        // Validate Enrollment ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Enrollment ID must be greater than zero"
            );
        }

        // Find Enrollment.
        Enrollment enrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found with id: " + id
                                )
                        );


        // Do not return Soft Deleted Enrollment.
        if (!enrollment.isActive()) {
            throw new ResourceNotFoundException(
                    "Enrollment not found with id: " + id
            );
        }

        return enrollment;
    }


    // Update an existing Enrollment.
    public Enrollment updateEnrollment(Long id,
                                       Date enrollmentDate,
                                       String status,
                                       Long studentId,
                                       Long courseId) {

        // Validate Enrollment data.
        validateEnrollmentData(
                enrollmentDate,
                status
        );

        // Get active Enrollment.
        Enrollment enrollmentToUpdate = getById(id);

        // Get active Student.
        Student student = studentService.getById(studentId);

        // Get active Course.
        Course course = courseService.getById(courseId);


        // Check if Student or Course relationship is being changed.
        boolean relationshipChanged =
                !enrollmentToUpdate.getStudent().getId().equals(studentId)
                        ||
                        !enrollmentToUpdate.getCourse().getId().equals(courseId);


        // Prevent creating duplicate Enrollment during Update.
        if (relationshipChanged) {

            boolean alreadyEnrolled =
                    enrollmentRepository
                            .existsByStudent_IdAndCourse_IdAndIsActiveTrue(
                                    studentId,
                                    courseId
                            );

            if (alreadyEnrolled) {
                throw new IllegalArgumentException(
                        "Student is already enrolled in this course"
                );
            }
        }


        // Update Enrollment fields.
        enrollmentToUpdate.setEnrollmentDate(enrollmentDate);
        enrollmentToUpdate.setStatus(status);

        // Update relationships.
        enrollmentToUpdate.setStudent(student);
        enrollmentToUpdate.setCourse(course);

        // Update modification date.
        enrollmentToUpdate.setUpdatedDate(new Date());

        // Save updated Enrollment.
        return enrollmentRepository.save(enrollmentToUpdate);
    }


    // Drop a Student Enrollment.
    public Enrollment dropEnrollment(Long enrollmentId) {

        // Get active Enrollment.
        Enrollment enrollment = getById(enrollmentId);

        // Set Enrollment status.
        enrollment.setStatus("Dropped");

        // Soft remove Enrollment.
        enrollment.setActive(false);

        // Update modification date.
        enrollment.setUpdatedDate(new Date());

        return enrollmentRepository.save(enrollment);
    }


    // Get all active Courses a Student is enrolled in.
    public List<Course> getCoursesByStudent(Long studentId) {

        // Make sure Student exists and is active.
        studentService.getById(studentId);

        return enrollmentRepository
                .findActiveCoursesByStudent(studentId);
    }


    // Get all active Students enrolled in a Course.
    public List<Student> getStudentsByCourse(Long courseId) {

        // Make sure Course exists and is active.
        courseService.getById(courseId);

        return enrollmentRepository
                .findActiveStudentsByCourse(courseId);
    }


    // Get all active Enrollments by Status.
    public List<Enrollment> getEnrollmentsByStatus(String status) {

        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Enrollment status cannot be blank"
            );
        }

        if (status.length() > 255) {
            throw new IllegalArgumentException(
                    "Enrollment status cannot exceed 255 characters"
            );
        }

        return enrollmentRepository
                .findActiveEnrollmentsByStatus(status);
    }


    // Get total number of actively enrolled Students in a Program.
    public long getTotalEnrolledStudentsByProgram(Long programId) {

        // Make sure Program exists and is active.
        programService.getById(programId);

        return enrollmentRepository
                .countActiveEnrolledStudentsByProgram(programId);
    }


    // Soft delete Enrollment by ID.
    public Boolean deleteById(Long id) {

        // Get active Enrollment.
        Enrollment enrollmentToDelete = getById(id);

        // Soft Delete.
        enrollmentToDelete.setActive(false);

        // Update modification date.
        enrollmentToDelete.setUpdatedDate(new Date());

        // Save changes.
        enrollmentRepository.save(enrollmentToDelete);

        return true;
    }
}