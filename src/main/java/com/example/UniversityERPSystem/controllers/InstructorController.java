package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.services.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        return null;
    }


    @GetMapping("/getAll")
    public List<Instructor> getAllInstructors() {
        return null;
    }


    @GetMapping("/getById/{id}")
    public Instructor getById(@PathVariable Long id) {
        return null;
    }



























}
