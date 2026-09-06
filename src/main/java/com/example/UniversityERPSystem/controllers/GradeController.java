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




}