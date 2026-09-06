package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.GuardianDTO;
import com.example.UniversityERPSystem.entities.Guardian;
import com.example.UniversityERPSystem.services.GuardianService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardian")
public class GuardianController {

    private final GuardianService guardianService;


    // Constructor Injection.
    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }


    // Add a new Guardian.
    @PostMapping("/add")
    public GuardianDTO addGuardian(
            @Valid @RequestBody GuardianDTO guardianDTO) {

        // Create Guardian Entity from DTO.
        Guardian guardian = new Guardian();

        guardian.setName(guardianDTO.getName());
        guardian.setRelationship(guardianDTO.getRelationship());
        guardian.setPhoneNumber(guardianDTO.getPhoneNumber());

        // Save Guardian using Student ID from DTO.
        Guardian savedGuardian = guardianService.addGuardian(
                guardian,
                guardianDTO.getStudentId()
        );

        // Return DTO instead of raw Entity.
        return GuardianDTO.convertToDTO(savedGuardian);
    }


    // Get all active Guardians.
    @GetMapping("/getAll")
    public List<GuardianDTO> getAllGuardians() {

        return GuardianDTO.convertToDTO(
                guardianService.getAllGuardians()
        );
    }


    // Get active Guardian by ID.
    @GetMapping("/getById/{id}")
    public GuardianDTO getById(
            @PathVariable Long id) {

        Guardian guardian = guardianService.getById(id);

        return GuardianDTO.convertToDTO(guardian);
    }


    // Update an existing Guardian.
    @PutMapping("/update/{id}")
    public GuardianDTO updateGuardian(
            @PathVariable Long id,
            @Valid @RequestBody GuardianDTO guardianDTO) {

        Guardian updatedGuardian =
                guardianService.updateGuardian(
                        id,
                        guardianDTO.getName(),
                        guardianDTO.getRelationship(),
                        guardianDTO.getPhoneNumber(),
                        guardianDTO.getStudentId()
                );

        // Return updated Guardian as DTO.
        return GuardianDTO.convertToDTO(updatedGuardian);
    }


    // Soft delete Guardian by ID.
    @DeleteMapping("/delete/{id}")
    public GuardianDTO deleteById(
            @PathVariable Long id) {

        // Get Guardian before Soft Delete.
        GuardianDTO guardianDTO =
                GuardianDTO.convertToDTO(
                        guardianService.getById(id)
                );

        // Perform Soft Delete.
        guardianService.deleteById(id);

        // Return DTO instead of raw Entity.
        return guardianDTO;
    }
}