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
        return studentService.addStudent(
                student,
                student.getProgram()
        );
    }


    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/getById/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(
                id,
                student.getName(),
                student.getGender(),
                student.getPhoneNumber(),
                student.getMajor(),
                student.getProgram()
        );
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return studentService.deleteById(id);
    }
}
