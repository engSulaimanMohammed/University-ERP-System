package com.example.UniversityERPSystem.controllers;



import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.services.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }




}
