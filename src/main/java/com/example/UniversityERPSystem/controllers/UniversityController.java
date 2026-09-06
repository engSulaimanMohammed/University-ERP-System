package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.services.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }


    @PostMapping("/add")
    public University addUniversity(@RequestBody University university) {
        return universityService.addUniversity(university);
    }


    @GetMapping("/getAll")
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }




}