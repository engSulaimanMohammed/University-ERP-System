package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.StudentDTO;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    // Constructor Injection.
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // Add a new Student.
    @PostMapping("/add")
    public StudentDTO addStudent(
            @Valid @RequestBody StudentDTO studentDTO) {

        // Create Student Entity from DTO.
        Student student = new Student();

        student.setName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setMajor(studentDTO.getMajor());

        // Save Student using Program ID from DTO.
        Student savedStudent = studentService.addStudent(
                student,
                studentDTO.getProgramId()
        );

        // Return DTO instead of raw Entity.
        return StudentDTO.convertToDTO(savedStudent);
    }


    // Get all active Students.
    @GetMapping("/getAll")
    public List<StudentDTO> getAllStudents() {

        // Convert List of Student Entities to DTOs.
        return StudentDTO.convertToDTO(
                studentService.getAllStudents()
        );
    }


    // Get active Student by ID.
    @GetMapping("/getById/{id}")
    public StudentDTO getById(@PathVariable Long id) {

        // Get Student from Service.
        Student student = studentService.getById(id);

        // Convert Entity to DTO.
        return StudentDTO.convertToDTO(student);
    }


    // Update an existing Student.
    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {

        // Update Student using DTO data.
        Student updatedStudent = studentService.updateStudent(
                id,
                studentDTO.getName(),
                studentDTO.getGender(),
                studentDTO.getPhoneNumber(),
                studentDTO.getMajor(),
                studentDTO.getProgramId()
        );

        // Return updated Student as DTO.
        return StudentDTO.convertToDTO(updatedStudent);
    }


    // Soft delete Student by ID.
    @DeleteMapping("/delete/{id}")
    public StudentDTO deleteById(@PathVariable Long id) {

        // Get Student before Soft Delete.
        StudentDTO studentDTO = StudentDTO.convertToDTO(
                studentService.getById(id)
        );

        // Perform Soft Delete.
        studentService.deleteById(id);

        // Return DTO instead of raw Entity.
        return studentDTO;
    }
}