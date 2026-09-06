package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.ProgramDTO;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.services.ProgramService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;


    // Constructor Injection.
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }


    // Add a new Program.
    @PostMapping("/add")
    public ProgramDTO addProgram(
            @Valid @RequestBody ProgramDTO programDTO) {

        // Create Program Entity from DTO.
        Program program = new Program();

        program.setName(programDTO.getName());
        program.setDegreeLevel(programDTO.getDegreeLevel());
        program.setDurationYears(programDTO.getDurationYears());

        // Save Program using Department ID from DTO.
        Program savedProgram = programService.addProgram(
                program,
                programDTO.getDepartmentId()
        );

        // Return DTO instead of raw Entity.
        return ProgramDTO.convertToDTO(savedProgram);
    }


    // Get all active Programs.
    @GetMapping("/getAll")
    public List<ProgramDTO> getAllPrograms() {

        // Convert List of Entities to List of DTOs.
        return ProgramDTO.convertToDTO(
                programService.getAllPrograms()
        );
    }


    // Get active Program by ID.
    @GetMapping("/getById/{id}")
    public ProgramDTO getById(@PathVariable Long id) {

        // Get Program from Service.
        Program program = programService.getById(id);

        // Convert Entity to DTO.
        return ProgramDTO.convertToDTO(program);
    }


    // Update an existing Program.
    @PutMapping("/update/{id}")
    public ProgramDTO updateProgram(
            @PathVariable Long id,
            @Valid @RequestBody ProgramDTO programDTO) {

        // Update Program using DTO data.
        Program updatedProgram = programService.updateProgram(
                id,
                programDTO.getName(),
                programDTO.getDegreeLevel(),
                programDTO.getDurationYears(),
                programDTO.getDepartmentId()
        );

        // Return updated Program as DTO.
        return ProgramDTO.convertToDTO(updatedProgram);
    }


    // Soft delete Program by ID.
    @DeleteMapping("/delete/{id}")
    public ProgramDTO deleteById(@PathVariable Long id) {

        // Get Program before soft deleting it.
        ProgramDTO programDTO = ProgramDTO.convertToDTO(
                programService.getById(id)
        );

        // Perform Soft Delete.
        programService.deleteById(id);

        // Return DTO instead of raw Entity.
        return programDTO;
    }
}