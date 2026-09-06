package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return null;
    }


    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return null;
    }










}
