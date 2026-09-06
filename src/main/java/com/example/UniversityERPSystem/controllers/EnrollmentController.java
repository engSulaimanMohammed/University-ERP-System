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
        return null;
    }



    @GetMapping("/getAll")
    public List<Enrollment> getAllEnrollments() {
        return null;
    }








}