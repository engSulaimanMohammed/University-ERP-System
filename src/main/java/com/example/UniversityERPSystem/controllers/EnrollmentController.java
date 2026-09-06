package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.services.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    @PostMapping("/add")
    public Enrollment addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.addEnrollment(
                enrollment,
                enrollment.getStudent(),
                enrollment.getCourse()
        );
    }



    @GetMapping("/getAll")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }


    @GetMapping("/getById/{id}")
    public Enrollment getById(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Enrollment updateEnrollment(@PathVariable Long id,
                                       @RequestBody Enrollment enrollment) {
        return enrollmentService.updateEnrollment(
                id,
                enrollment.getEnrollmentDate(),
                enrollment.getStatus(),
                enrollment.getStudent(),
                enrollment.getCourse()
        );
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return enrollmentService.deleteById(id);
    }
}