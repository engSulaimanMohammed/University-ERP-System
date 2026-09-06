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
        return instructorService.addInstructor(
                instructor,
                instructor.getDepartment()
        );    }


    @GetMapping("/getAll")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }


    @GetMapping("/getById/{id}")
    public Instructor getById(@PathVariable Long id) {
        return instructorService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Instructor updateInstructor(@PathVariable Long id,
                                       @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(
                id,
                instructor.getName(),
                instructor.getEmail(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization(),
                instructor.getDepartment()
        );
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return instructorService.deleteById(id);
    }
}


