package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.FacultyDTO;
import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.services.FacultyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;


    // Constructor Injection.
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    // Add a new Faculty.
    @PostMapping("/add")
    public FacultyDTO addFaculty(
            @Valid @RequestBody FacultyDTO facultyDTO) {

        // Create Faculty Entity from the received DTO.
        Faculty faculty = new Faculty();

        faculty.setName(facultyDTO.getName());
        faculty.setDescription(facultyDTO.getDescription());

        // Save Faculty using University ID from the DTO.
        Faculty savedFaculty = facultyService.addFaculty(
                faculty,
                facultyDTO.getUniversityId()
        );

        // Return DTO instead of raw Entity.
        return FacultyDTO.convertToDTO(savedFaculty);
    }


    // Get all active Faculties.
    @GetMapping("/getAll")
    public List<FacultyDTO> getAllFaculties() {

        return FacultyDTO.convertToDTO(
                facultyService.getAllFaculties()
        );
    }


    // Get active Faculty by ID.
    @GetMapping("/getById/{id}")
    public FacultyDTO getById(@PathVariable Long id) {

        Faculty faculty = facultyService.getById(id);

        return FacultyDTO.convertToDTO(faculty);
    }


    // Update an existing Faculty.
    @PutMapping("/update/{id}")
    public FacultyDTO updateFaculty(
            @PathVariable Long id,
            @Valid @RequestBody FacultyDTO facultyDTO) {

        Faculty updatedFaculty = facultyService.updateFaculty(
                id,
                facultyDTO.getName(),
                facultyDTO.getDescription(),
                facultyDTO.getUniversityId()
        );

        // Return updated Faculty as DTO.
        return FacultyDTO.convertToDTO(updatedFaculty);
    }


    // Soft delete Faculty by ID.
    @DeleteMapping("/delete/{id}")
    public FacultyDTO deleteById(@PathVariable Long id) {

        // Get Faculty before soft deleting it.
        FacultyDTO facultyDTO = FacultyDTO.convertToDTO(
                facultyService.getById(id)
        );

        // Perform soft delete.
        facultyService.deleteById(id);

        // Return DTO instead of raw Entity.
        return facultyDTO;
    }
}