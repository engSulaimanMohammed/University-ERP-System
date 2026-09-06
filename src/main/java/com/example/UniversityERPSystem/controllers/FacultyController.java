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

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return null;
    }


    @GetMapping("/getAll")
    public List<Faculty> getAllFaculties() {
        // Call getAllFaculties.
        return null;
    }











}
