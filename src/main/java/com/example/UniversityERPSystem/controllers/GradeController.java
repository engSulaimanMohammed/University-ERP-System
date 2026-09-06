package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.services.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @PostMapping("/add")
    public Grade addGrade(@RequestBody Grade grade) {
        return null;
    }


    @GetMapping("/getAll")
    public List<Grade> getAllGrades() {
        return null;
    }


    @GetMapping("/getById/{id}")
    public Grade getById(@PathVariable Long id) {
        return null;
    }



    @PutMapping("/update/{id}")
    public Grade updateGrade(@PathVariable Long id,
                             @RequestBody Grade grade) {
        return null;
    }














}