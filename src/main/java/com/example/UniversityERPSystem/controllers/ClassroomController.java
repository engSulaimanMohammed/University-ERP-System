package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.ClassroomDTO;
import com.example.UniversityERPSystem.entities.Classroom;
import com.example.UniversityERPSystem.services.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;


    // Constructor Injection.
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    // Add a new Classroom.
    @PostMapping("/add")
    public ClassroomDTO addClassroom(
            @Valid @RequestBody ClassroomDTO classroomDTO) {

        // Create Classroom Entity from DTO.
        Classroom classroom = new Classroom();

        classroom.setRoomNumber(classroomDTO.getRoomNumber());
        classroom.setFloor(classroomDTO.getFloor());
        classroom.setCapacity(classroomDTO.getCapacity());

        // Save Classroom using Department ID from DTO.
        Classroom savedClassroom =
                classroomService.addClassroom(
                        classroom,
                        classroomDTO.getDepartmentId()
                );

        // Return DTO instead of raw Entity.
        return ClassroomDTO.convertToDTO(savedClassroom);
    }


    // Get all active Classrooms.
    @GetMapping("/getAll")
    public List<ClassroomDTO> getAllClassrooms() {

        return ClassroomDTO.convertToDTO(
                classroomService.getAllClassrooms()
        );
    }


    // Get active Classroom by ID.
    @GetMapping("/getById/{id}")
    public ClassroomDTO getById(
            @PathVariable Long id) {

        Classroom classroom =
                classroomService.getById(id);

        return ClassroomDTO.convertToDTO(classroom);
    }


    // Update an existing Classroom.
    @PutMapping("/update/{id}")
    public ClassroomDTO updateClassroom(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomDTO classroomDTO) {

        Classroom updatedClassroom =
                classroomService.updateClassroom(
                        id,
                        classroomDTO.getRoomNumber(),
                        classroomDTO.getFloor(),
                        classroomDTO.getCapacity(),
                        classroomDTO.getDepartmentId()
                );

        return ClassroomDTO.convertToDTO(
                updatedClassroom
        );
    }


    // Soft delete Classroom by ID.
    @DeleteMapping("/delete/{id}")
    public ClassroomDTO deleteById(
            @PathVariable Long id) {

        // Get Classroom before Soft Delete.
        ClassroomDTO classroomDTO =
                ClassroomDTO.convertToDTO(
                        classroomService.getById(id)
                );

        // Perform Soft Delete.
        classroomService.deleteById(id);

        // Return DTO.
        return classroomDTO;
    }
}