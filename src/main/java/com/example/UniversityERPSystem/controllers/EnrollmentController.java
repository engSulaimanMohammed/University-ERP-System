package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.CourseDTO;
import com.example.UniversityERPSystem.dtos.DropEnrollmentDTO;
import com.example.UniversityERPSystem.dtos.EnrollmentDTO;
import com.example.UniversityERPSystem.dtos.ProgramEnrollmentStatsDTO;
import com.example.UniversityERPSystem.dtos.StudentDTO;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;


    // Constructor Injection.
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    // Enroll a Student in a Course.
    @PostMapping("/add")
    public EnrollmentDTO addEnrollment(
            @Valid @RequestBody EnrollmentDTO enrollmentDTO) {

        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollmentDate(
                enrollmentDTO.getEnrollmentDate()
        );

        enrollment.setStatus(
                enrollmentDTO.getStatus()
        );

        Enrollment savedEnrollment =
                enrollmentService.addEnrollment(
                        enrollment,
                        enrollmentDTO.getStudentId(),
                        enrollmentDTO.getCourseId()
                );

        return EnrollmentDTO.convertToDTO(savedEnrollment);
    }


    // Get all active Enrollments.
    @GetMapping("/getAll")
    public List<EnrollmentDTO> getAllEnrollments() {

        return EnrollmentDTO.convertToDTO(
                enrollmentService.getAllEnrollments()
        );
    }


    // Get active Enrollment by ID.
    @GetMapping("/getById/{id}")
    public EnrollmentDTO getById(
            @PathVariable Long id) {

        return EnrollmentDTO.convertToDTO(
                enrollmentService.getById(id)
        );
    }


    // Update an existing Enrollment.
    @PutMapping("/update/{id}")
    public EnrollmentDTO updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentDTO enrollmentDTO) {

        Enrollment updatedEnrollment =
                enrollmentService.updateEnrollment(
                        id,
                        enrollmentDTO.getEnrollmentDate(),
                        enrollmentDTO.getStatus(),
                        enrollmentDTO.getStudentId(),
                        enrollmentDTO.getCourseId()
                );

        return EnrollmentDTO.convertToDTO(
                updatedEnrollment
        );
    }


    // Drop an Enrollment.
    @PutMapping("/drop")
    public EnrollmentDTO dropEnrollment(
            @Valid @RequestBody DropEnrollmentDTO dropEnrollmentDTO) {

        Enrollment droppedEnrollment =
                enrollmentService.dropEnrollment(
                        dropEnrollmentDTO.getEnrollmentId()
                );

        return EnrollmentDTO.convertToDTO(
                droppedEnrollment
        );
    }


    // Get Courses a Student is enrolled in.
    @GetMapping("/coursesByStudent/{studentId}")
    public List<CourseDTO> getCoursesByStudent(
            @PathVariable Long studentId) {

        return CourseDTO.convertToDTO(
                enrollmentService.getCoursesByStudent(studentId)
        );
    }


    // Get Students enrolled in a Course.
    @GetMapping("/studentsByCourse/{courseId}")
    public List<StudentDTO> getStudentsByCourse(
            @PathVariable Long courseId) {

        return StudentDTO.convertToDTO(
                enrollmentService.getStudentsByCourse(courseId)
        );
    }


    // Get Enrollments by Status.
    @GetMapping("/byStatus/{status}")
    public List<EnrollmentDTO> getEnrollmentsByStatus(
            @PathVariable String status) {

        return EnrollmentDTO.convertToDTO(
                enrollmentService.getEnrollmentsByStatus(status)
        );
    }


    // Get Program Enrollment statistics.
    @GetMapping("/programStats/{programId}")
    public ProgramEnrollmentStatsDTO getProgramEnrollmentStats(
            @PathVariable Long programId) {

        long totalEnrolledStudents =
                enrollmentService
                        .getTotalEnrolledStudentsByProgram(programId);

        return ProgramEnrollmentStatsDTO.builder()
                .programId(programId)
                .totalEnrolledStudents(totalEnrolledStudents)
                .build();
    }


    // Soft delete Enrollment.
    @DeleteMapping("/delete/{id}")
    public EnrollmentDTO deleteById(
            @PathVariable Long id) {

        EnrollmentDTO enrollmentDTO =
                EnrollmentDTO.convertToDTO(
                        enrollmentService.getById(id)
                );

        enrollmentService.deleteById(id);

        return enrollmentDTO;
    }
}