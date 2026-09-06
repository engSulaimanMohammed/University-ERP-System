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

        return facultyService.addFaculty(faculty, faculty.getUniversity());
    }


    @GetMapping("/getAll")
    public List<Faculty> getAllFaculties() {
        // Call getAllFaculties.
        return facultyService.getAllFaculties();
    }


    @GetMapping("/getById/{id}")
    public Faculty getById(@PathVariable Long id) {
        // Call getById.
        return facultyService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Faculty updateFaculty(@PathVariable Long id,
                                 @RequestBody Faculty faculty) {
        // Call updateFaculty from FacultyService
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        // Call deleteById from FacultyService
        return null;
    }
}
