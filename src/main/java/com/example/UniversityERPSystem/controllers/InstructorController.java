package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.InstructorDTO;
import com.example.UniversityERPSystem.dtos.InstructorStatsDTO;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.services.InstructorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;


    // Constructor Injection.
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    // Add a new Instructor.
    @PostMapping("/add")
    public InstructorDTO addInstructor(
            @Valid @RequestBody InstructorDTO instructorDTO) {

        // Create Instructor Entity from DTO.
        Instructor instructor = new Instructor();

        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        instructor.setSpecialization(instructorDTO.getSpecialization());

        // Save Instructor using Department ID from DTO.
        Instructor savedInstructor = instructorService.addInstructor(
                instructor,
                instructorDTO.getDepartmentId()
        );

        // Return DTO instead of raw Entity.
        return InstructorDTO.convertToDTO(savedInstructor);
    }


    // Get all active Instructors.
    @GetMapping("/getAll")
    public List<InstructorDTO> getAllInstructors() {

        return InstructorDTO.convertToDTO(
                instructorService.getAllInstructors()
        );
    }


    // Get active Instructor by ID.
    @GetMapping("/getById/{id}")
    public InstructorDTO getById(@PathVariable Long id) {

        Instructor instructor = instructorService.getById(id);

        return InstructorDTO.convertToDTO(instructor);
    }


    // Update an existing Instructor.
    @PutMapping("/update/{id}")
    public InstructorDTO updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody InstructorDTO instructorDTO) {

        Instructor updatedInstructor =
                instructorService.updateInstructor(
                        id,
                        instructorDTO.getName(),
                        instructorDTO.getEmail(),
                        instructorDTO.getPhoneNumber(),
                        instructorDTO.getSpecialization(),
                        instructorDTO.getDepartmentId()
                );

        // Return updated Instructor as DTO.
        return InstructorDTO.convertToDTO(updatedInstructor);
    }


    // Get Instructor statistics.
    @GetMapping("/stats/{id}")
    public InstructorStatsDTO getInstructorStats(
            @PathVariable Long id) {

        // Get active Instructor.
        Instructor instructor = instructorService.getById(id);

        // Get total active Courses taught.
        int totalCourses =
                instructorService.getTotalCoursesTaught(id);

        // Return statistics as DTO.
        return InstructorStatsDTO.builder()
                .instructorId(instructor.getId())
                .instructorName(instructor.getName())
                .totalCoursesTaught(totalCourses)
                .build();
    }


    // Soft delete Instructor by ID.
    @DeleteMapping("/delete/{id}")
    public InstructorDTO deleteById(@PathVariable Long id) {

        // Get Instructor before Soft Delete.
        InstructorDTO instructorDTO =
                InstructorDTO.convertToDTO(
                        instructorService.getById(id)
                );

        // Perform Soft Delete.
        instructorService.deleteById(id);

        // Return DTO instead of raw Entity.
        return instructorDTO;
    }
}