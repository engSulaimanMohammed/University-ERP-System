package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.UniversityDTO;
import com.example.UniversityERPSystem.dtos.UniversityStatsDTO;
import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.services.UniversityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;


    // Constructor Injection.
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }


    // Add a new University.
    @PostMapping("/add")
    public UniversityDTO addUniversity(
            @Valid @RequestBody UniversityDTO universityDTO) {

        // Create University Entity from DTO.
        University university = new University();

        university.setName(universityDTO.getName());
        university.setLocation(universityDTO.getLocation());

        // Save University.
        University savedUniversity =
                universityService.addUniversity(university);

        // Return DTO.
        return UniversityDTO.convertToDTO(savedUniversity);
    }


    // Get all active Universities.
    @GetMapping("/getAll")
    public List<UniversityDTO> getAllUniversities() {

        return UniversityDTO.convertToDTO(
                universityService.getAllUniversities()
        );
    }


    // Get active University by ID.
    @GetMapping("/getById/{id}")
    public UniversityDTO getById(
            @PathVariable Long id) {

        University university =
                universityService.getById(id);

        return UniversityDTO.convertToDTO(university);
    }


    // Update an existing University.
    @PutMapping("/update/{id}")
    public UniversityDTO updateUniversity(
            @PathVariable Long id,
            @Valid @RequestBody UniversityDTO universityDTO) {

        University updatedUniversity =
                universityService.updateUniversity(
                        id,
                        universityDTO.getName(),
                        universityDTO.getLocation()
                );

        return UniversityDTO.convertToDTO(
                updatedUniversity
        );
    }


    // Get University statistics.
    @GetMapping("/stats/{id}")
    public UniversityStatsDTO getUniversityStats(
            @PathVariable Long id) {

        return universityService.getUniversityStats(id);
    }


    // Soft delete University by ID.
    @DeleteMapping("/delete/{id}")
    public UniversityDTO deleteById(
            @PathVariable Long id) {

        // Get University before Soft Delete.
        UniversityDTO universityDTO =
                UniversityDTO.convertToDTO(
                        universityService.getById(id)
                );

        // Perform Soft Delete.
        universityService.deleteById(id);

        return universityDTO;
    }
}